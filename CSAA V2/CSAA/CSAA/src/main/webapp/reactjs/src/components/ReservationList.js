import React, {Component} from 'react';
import {Button, ButtonGroup, Card, Table} from 'react-bootstrap';
import axios from 'axios';



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
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
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
                                    <td colSpan="6"> rezervací</td>
                                </tr> :
                                this.state.reservations.map((reservation) =>(
                                    <tr key={reservation.id}>
                                        <td>{reservation.name}</td>
                                        <td>{reservation.phone}</td>
                                        <td>{reservation.spz}</td>
                                        <td>{reservation.date}</td>
                                        <td>{reservation.timeIndex}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="sm" onClick={this.deleteReservation.bind(this, reservation.id)}>Smazat</Button>
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                ))

                        }
                        </tbody>
                    </Table>
                </Card.Body>

            </Card>
        );
    }
}