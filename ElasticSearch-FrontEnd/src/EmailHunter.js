import React, { Component } from 'react'
import axios from "axios";
import {Button, FormControlLabel, FormControl, FormLabel, RadioGroup, Radio } from '@material-ui/core/';

export default class EmailHunter extends Component {
    constructor() {
        super();
        this.state = {
            elasticsearch : [],
            email: '',
            searchOption: 'email',
            searchSize : '5'
        };
    }

    setEmail = (event) => {
        this.setState({ email : event.target.value});
    };

    setSearchSize = (event) => {
        this.setState({ searchSize : event.target.value});
    };

    setSearchOption = (event) => {
        this.setState({ searchOption : event.target.value});
    };

    downloadJSONFile = async () => {
        axios({
            url: '/downloadJSON',
            method: 'GET',
            responseType: 'blob',
          }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'JSONResults.txt');
            document.body.appendChild(link);
            link.click();
          });  
    }

    handleOnClick = async () => {
        if (this.state.email.length === 0) {
           return; 
        }
        if (this.state.searchOption === "email") {
            await axios.get("/queryByEmail?email=" + this.state.email + "&size=" + this.state.searchSize).then(response => {
                this.setState({elasticsearch : response.data});
            });
        }
        if (this.state.searchOption === "username") {
            await axios.get("/queryByUsername?username=" + this.state.email + "&size=" + this.state.searchSize).then(response => {
                this.setState({elasticsearch : response.data});
            });
        }

        if (this.state.searchOption === "ip") {
            await axios.get("/queryByIP?ip=" + this.state.email + "&size=" + this.state.searchSize).then(response => {
                this.setState({elasticsearch : response.data});
            });
        }
    }


    render() {
        let listItems = "";
        if(this.state.elasticsearch !== 'RANGE-ERROR'){
            listItems = this.state.elasticsearch.map((document) =>
                <div className="results-list">
                    <table key={document._source._id}><tbody>
                    <tr><td>Index:</td><td>{document._index}</td></tr>
                    <tr><td>Email:</td><td>{document._source.Email}</td></tr>
                    <tr><td>Password:</td><td>{document._source.Password}</td></tr>
                    <tr><td>Username:</td><td>{document._source.Username}</td></tr>
                    <tr><td>Name:</td><td>{document._source.Name}</td></tr>
                    <tr><td>IP Address:</td><td>{document._source["IP Address"]}</td></tr>
                    </tbody></table>
                </div>);
        }
        if(this.state.elasticsearch === 'RANGE-ERROR'){
            listItems = 'Search string must be between 1 and 100 characters';
        }
        return (
            <div>
                <div className="container">
                    <div className="searchBar">
                        <h2>Search:</h2>
                        <input onChange={this.setEmail.bind(this)} type="text" /><Button onClick={this.handleOnClick} variant="contained" color="primary">Search</Button><br />
                    <FormControl component="fieldset">
                        <FormLabel component="legend">Size</FormLabel>
                        <RadioGroup aria-label="size" name="size">
                            <FormControlLabel value="5" onClick={this.setSearchSize.bind(this)} control={<Radio />} label="5" />
                            <FormControlLabel value="100" onClick={this.setSearchSize.bind(this)} control={<Radio />} label="100" />
                            <FormControlLabel value="10000" onClick={this.setSearchSize.bind(this)} control={<Radio />} label="10000" />
                        </RadioGroup>
                    </FormControl>
                        <hr />
                        <FormControl component="fieldset">
                        <FormLabel component="legend">Search Type</FormLabel>
                        <RadioGroup aria-label="size" name="size">
                            <FormControlLabel value="email" onClick={this.setSearchOption.bind(this)} control={<Radio />} label="Email" />
                            <FormControlLabel value="ip" onClick={this.setSearchOption.bind(this)} control={<Radio />} label="IP Address" />
                            <FormControlLabel value="username" onClick={this.setSearchOption.bind(this)} control={<Radio />} label="Username" />
                        </RadioGroup>
                    </FormControl>
                    </div>
                    <div className="results">
                        <div className="results-header">
                            <h2>Results # of {this.state.elasticsearch.length}</h2>
                            <Button onClick={this.downloadJSONFile} variant="contained" color="primary" href="#contained-buttons">Download</Button>
                        </div>
                        <br />
                        {listItems}
                        <br /><br />
                    </div>
                </div>
            </div>
        )
    }
}