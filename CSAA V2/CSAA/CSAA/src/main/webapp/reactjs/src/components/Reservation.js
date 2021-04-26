import React, {Component} from 'react';
import {Row, Container, Button, Card, Col, Form, InputGroup, FormControl} from "react-bootstrap";
import axios from 'axios';

export default class ReservationList extends Component {

    constructor(props) {
        super(props);
        this.state = {name:'', spz:'', phone:'', date:'', time:''};
        this.reservationChange = this.reservationChange.bind(this);
        this.submitReservation = this.submitReservation.bind(this);
    }

    submitReservation = event => {

        debugger;
        alert("Name: " + this.state.name + " Phone: " + this.state.phone + " Spz: " + this.state.spz + " Date: " + this.state.date + " Time: " + this.state.time);
        event.preventDefault();

        const reservation = {
            id: 0,
            name: this.state.name,
            phone: this.state.phone,
            spz: this.state.spz,
            date: this.state.date,
            time: this.getTimeIndex(this.state.time)
        };

        axios.post("http://localhost:8080/reservations", reservation)
            .then(response => {
                if(response.data != null){
                    alert("Reservation submitted");
                }
            })
    }

    getTimeIndex(time){
        switch (time){
            case "08:00":
                return 1;
                break;
            case "09:00":
                return 2;
                break;
            case "10:00":
                return 3;
                break;
            case "11:00":
                return 4;
                break;
            case "12:00":
                return 5;
                break;
            case "13:00":
                return 6;
                break;
            case "14:00":
                return 7;
                break;
            case "15:00":
                return 8;
                break;
            case "16:00":
                return 9;
                break;
            default:
                return 0;
        }

    }



    reservationChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render(){
        const {name, phone, spz, date, time} = this.state;

        return (
<Row className="justify-content-md-center">
         <Card className={"border border-dark bg-dark text-white w-50 mt-5"}>
             <Card.Header className="text-center text-uppercase font-weight-bold">Přidat rezervaci</Card.Header>
             <Form onSubmit={this.submitReservation} id="bookFormId">
                 <Card.Body>
                     <Container>
                         <Form.Group as={Col} controlId="formName">
                             <InputGroup className="mb-3">
                                 <InputGroup.Prepend>
                                     <InputGroup.Text id="basic-addon1" className="font-weight-bold">Jméno</InputGroup.Text>
                                 </InputGroup.Prepend>
                                 <Form.Control required
                                               name="name"
                                               type="name"
                                               value={name}
                                               onChange={this.reservationChange}
                                               placeholder="Vložte jméno"
                                 />
                             </InputGroup>
                         </Form.Group>

                         <Form.Group as={Col} controlId="formPhone">
                             <InputGroup className="my-4">
                                 <InputGroup.Prepend>
                                     <InputGroup.Text id="basic-addon1" className="font-weight-bold">Telefon</InputGroup.Text>
                                 </InputGroup.Prepend>
                             <Form.Control required
                                           name="phone"
                                           type="tel"
                                           value={phone}
                                           onChange={this.reservationChange}

                                           placeholder="Vložte tel. číslo" />
                             </InputGroup>
                         </Form.Group>

                         <Form.Group as={Col} controlId="formSpz">
                             <InputGroup className="my-4">
                                 <InputGroup.Prepend>
                                     <InputGroup.Text id="basic-addon1" className="font-weight-bold">SPZ</InputGroup.Text>
                                 </InputGroup.Prepend>

                             <Form.Control required
                                           name="spz"
                                           type="name"
                                           value={spz}
                                           onChange={this.reservationChange}
                                           placeholder="Vložte spz" />
                             </InputGroup>
                         </Form.Group>
                     </Container>
                     <Container>
                         <Row>
                             <Col> <Form.Group as={Col} controlId="formDate">

                                 <Form.Control required
                                               name="date"
                                               type="date"
                                               value={date}
                                               onChange={this.reservationChange}
                                               placeholder="Date" />
                             </Form.Group></Col>


                             <Col>

                                 <Form.Group as={Col} controlId="formTime">
                              
                                     <Form.Control as="select" custom  required
                                                   name="time"
                                                   type="time"
                                                   value={time}
                                                   onChange={this.reservationChange}
                                                   label="Time">
                                         <option>08:00</option>
                                         <option>09:00</option>
                                         <option>10:00</option>
                                         <option>11:00</option>
                                         <option>12:00</option>
                                         <option>13:00</option>
                                         <option>14:00</option>
                                         <option>15:00</option>
                                         <option>16:00</option>
                                         />
                                     </Form.Control>
                                 </Form.Group>


                     </Col>
                         </Row>



                     </Container><Container>
                         <Button variant="secondary mx-3 " type="submit">
                             Rezervovat
                         </Button>
                 </Container>
                 </Card.Body>
             </Form>
         </Card></Row>
        );
    }
}