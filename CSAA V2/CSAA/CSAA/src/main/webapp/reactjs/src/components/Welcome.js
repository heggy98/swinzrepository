import React from 'react';
import {Jumbotron} from "react-bootstrap";


class Welcome extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-dark text-white">
                <h1>Hello!</h1>
                <p>
                    Welcome to the Car service administration / application!
                </p>
            </Jumbotron>
        );
    }
}

export default Welcome