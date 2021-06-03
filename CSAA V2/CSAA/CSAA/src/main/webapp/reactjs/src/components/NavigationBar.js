import React from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from "react-router-dom";

class NavigationBar extends React.Component {

    render(){
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img src="src//main//resources//ico.png" alt=''/>Car service adminstration / application
                </Link>
                <Nav className="mr-auto">
                    <Link to={"add"} className="nav-link">Nová rezervace</Link>
                </Nav>
                <Nav className="mr-auto">
                    <Link to={"list"} className="nav-link">Rezervace</Link>
                </Nav>
            </Navbar>
        )
    }

}

export default NavigationBar