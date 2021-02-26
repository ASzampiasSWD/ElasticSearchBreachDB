import React, { Component } from 'react'
import axios from "axios";

export default class EmailHunter extends Component {
    constructor() {
        super();
        this.state = {
            elasticsearch : 'Not yet gotten',
            email: ''
        };
    }

    /*componentDidMount = () => {
        console.log("Loaded!!!");
    };*/

    setEmail = (event) => {
        this.setState({ email : event.target.value});
    };

    handleOnClick = async () => {
        console.log(this.state.email);
        await axios.get("/queryByEmail?email=" + this.state.email).then(response => {
            console.log(response);
            this.setState({elasticsearch : response.data
            });
        });
    }

    render() {
        return (
            <div>
                <input onChange={this.setEmail.bind(this)} type="text" />
                <button onClick={this.handleOnClick}>Get Passwords</button>
                <h1>Email Info</h1>
                <textarea rows="50" cols="100" value={this.state.elasticsearch}></textarea>
                <br /><br />
            </div>
        )
    }

}