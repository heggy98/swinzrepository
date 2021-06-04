import React, {Component} from 'react';
import {Button, ButtonGroup, Card, Jumbotron, Row, Table, Col} from 'react-bootstrap';
import axios from 'axios';
import {Link} from "react-router-dom";




export default class ReservationList extends Component {

    constructor(props) {
        super(props);
        this.state ={
            reservations : []
        }
    }


    componentDidMount() {
        this.findAllReservations()
    }

    findAllReservations(){
        axios.get("http://localhost:8080/reservations")
            .then(response => response.data)
            .then((data) => {
                this.setState({reservations: data});
            })
    }


    deleteReservation = (reservationId) =>{
        axios.delete("http://localhost:8080/reservations/"+reservationId)
            .then(response =>{
                if(response.data != null){
                    alert("Smazáno!");
                    this.setState({
                        reservations: this.state.reservations.filter(reservation => reservation.id !== reservationId)
                    });
                }
            })
    };

    render(){
        return (
            <Jumbotron className="hero">
                <Row className={"mb-3"}>
                <Col>
                <h1>Seznam rezervací</h1>
                </Col>
                    <Col className={"fr"}>
                        <Link to={""} className="btn btn-info btn-lg">
                          Domů
                        </Link>
                    {' '}
                        <Link to={"add"} className="btn btn-info btn-lg">
                            Nová rezervace
                        </Link>
                    </Col>
                </Row>
            <Card className={"border "}>
                <Card.Body>
                    <Table bordered hover striped responsive>
                        <thead>
                        <tr>
                            <th>Jméno</th>
                            <th>Telefonní číslo</th>
                            <th>SPZ</th>
                            <th>Datum</th>
                            <th>Čas</th>
                            <th>Úpravy</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.reservations.length === 0 ?
                                <tr align="center">
                                    <td colSpan="6"> 0 rezervací</td>
                                </tr> :
                                this.state.reservations.map((reservation) =>(
                                    <tr key={reservation.id}>
                                        <td>{reservation.name}</td>
                                        <td>{reservation.phone}</td>
                                        <td>{reservation.spz}</td>
                                        <td>{reservation.date}</td>
                                        <td>{reservation.time}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Link to={"edit/"+reservation.id} className="btn btn-sm btn-info">Upravit</Link>{' '}
                                                <Button variant="danger" size="sm" onClick={this.deleteReservation.bind(this, reservation.id)}>✕</Button>

                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                ))

                        }
                        </tbody>
                    </Table>
                </Card.Body>

            </Card>
            </Jumbotron>
        );
    }
}