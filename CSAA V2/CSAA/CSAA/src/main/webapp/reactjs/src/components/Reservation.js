import React, {Component} from 'react';
import {Button, Card, Col, Form} from "react-bootstrap";
import axios from 'axios';

export default class ReservationList extends Component {

    constructor(props) {
        super(props);
        this.state = {name:'', spz:'', phone:'', date:'', time:''};
        this.reservationChange = this.reservationChange.bind(this);
        this.submitReservation = this.submitReservation.bind(this);
    }

    submitReservation = event => {
        alert("Name: " + this.state.name + " Phone: " + this.state.phone + " Spz: " + this.state.spz + " Date: " + this.state.date + " Time: " + this.state.time);
        event.preventDefault();

        const reservation = {
            name: this.state.name,
            Phone: this.state.phone,
            Spz: this.state.spz,
            Date: this.state.date,
            Time: this.state.time
        };

        axios.post("http://localhost:8080/reservations", reservation)
            .then(response => {
                if(response.data != null){
                    alert("Reservation submitted");
                }
            })
    }

    reservationChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render(){
        const {name, phone, spz, date, time} = this.state;

        return (
         <Card className={"border border-dark bg-dark text-white"}>
             <Card.Header>Add reservation</Card.Header>
             <Form onSubmit={this.submitReservation} id="bookFormId">
                 <Card.Body>
                     <Form.Row>
                         <Form.Group as={Col} controlId="formName">
                             <Form.Label>Name</Form.Label>
                             <Form.Control required
                                           name="name"
                                           type="name"
                                           value={name}
                                           onChange={this.reservationChange}
                                           placeholder="Enter name" />
                         </Form.Group>

                         <Form.Group as={Col} controlId="formPhone">
                             <Form.Label>Phone</Form.Label>
                             <Form.Control required
                                           name="phone"
                                           type="tel"
                                           value={phone}
                                           onChange={this.reservationChange}
                                           placeholder="Enter telephone" />
                         </Form.Group>

                         <Form.Group as={Col} controlId="formSpz">
                             <Form.Label>SPZ</Form.Label>
                             <Form.Control required
                                           name="spz"
                                           type="name"
                                           value={spz}
                                           onChange={this.reservationChange}
                                           placeholder="Enter spz" />
                         </Form.Group>

                         <Form.Group as={Col} controlId="formDate">
                             <Form.Label>Date</Form.Label>
                             <Form.Control required
                                           name="date"
                                           type="date"
                                           value={date}
                                           onChange={this.reservationChange}
                                           placeholder="Date" />
                         </Form.Group>

                         <Form.Group as={Col} controlId="formTime">
                             <Form.Label>Time</Form.Label>
                             <Form.Control required
                                           name="time"
                                           type="time"
                                           value={time}
                                           onChange={this.reservationChange}
                                           label="Time" />
                         </Form.Group>

                         <Button variant="success" type="submit">
                             Submit
                         </Button>
                     </Form.Row>
                 </Card.Body>
             </Form>
         </Card>
        );
    }
}