import React from 'react';
import {Jumbotron, Row, Col} from "react-bootstrap";
import {Link} from "react-router-dom";


class Welcome extends React.Component {
    render() {
        return (
            <Jumbotron className="hero">
                <Row>
                    <Col> <img className="Welcome-image" src="https://i.imgur.com/cJeoZ6E.png" alt=''/>
                    </Col>
                    <Col>
                        <h1>Rezervační systém</h1>
                        <h2>Car service adminstration / application</h2>
                        <p>
                           Aplikace pro správu rezervací v autoservisu
                        </p>
                        <Link to={"add"} className="btn btn-lg btn-info">Nová rezervace</Link>
                       {' '}
                        <Link to={"list"} className="btn btn-lg btn-info">Seznam</Link>

                    </Col>
                </Row>

            </Jumbotron>
        );
    }
}

export default Welcome