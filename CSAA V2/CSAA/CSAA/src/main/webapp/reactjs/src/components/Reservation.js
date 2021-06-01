import React, {Component} from 'react';
import {Row, Container, Button, Card, Col, Form, InputGroup, FormControl} from "react-bootstrap";
import axios from 'axios';

export default class ReservationList extends Component {

    constructor(props) {
        super(props);
        this.state = {name: '', spz: '', phone: '', date: '', time: '', times: []};
        this.reservationChange = this.reservationChange.bind(this);
        this.submitReservation = this.submitReservation.bind(this);
    }

    componentDidMount() {
        this.getTimeIndexes();
    }

    submitReservation = event => {

        event.preventDefault();

        const reservation = {
            id: 0,
            name: this.state.name,
            phone: this.state.phone,
            spz: this.state.spz,
            date: this.state.date,
            time: this.state.time
        };

        axios.post("http://localhost:8080/reservations", reservation)
            .then(response => {
                if (response.data != null) {
                    alert("Reservation submitted");
                }
            })
            .catch(function (error) {

                alert("Selected datetime is full! Please select another time / date!")

                if (error.response) {
                    // Request made and server responded
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }

            });
    }

    getTimeIndexes() {
        axios.get("http://localhost:8080/times")
            .then(response => response.data)
            .then((data) => {
                if (data != null) {
                    this.setState({times: data});
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }


    // Function for future purposes
    // getFreeTimeIndexesByDate() {
    //
    //     axios.get("http://localhost:8080/times/")
    //         .then(response => response.data)
    //         .then((data) => {
    //             if (data != null) {
    //                 this.setState({times: data});
    //             }
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // }

    reservationChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {
        const {name, phone, spz, date, time, times} = this.state;

        let timesList = Object.keys(times).map((k) => {
            return (
                <option key={k} value={k}>{times[k]}</option>
            )
        }, this);

        return (
            <Row className="justify-content-md-center">
                <Card className={"border border-dark bg-dark text-white w-50 mt-5"}>
                    <Card.Header className="text-center text-uppercase font-weight-bold">Přidat
                        rezervaci</Card.Header>
                    <Form onSubmit={this.submitReservation} id="bookFormId">
                        <Card.Body>
                            <Container>
                                <Form.Group as={Col} controlId="formName">
                                    <InputGroup className="mb-3">
                                        <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1"
                                                             className="font-weight-bold">Jméno</InputGroup.Text>
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
                                            <InputGroup.Text id="basic-addon1"
                                                             className="font-weight-bold">Telefon</InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control required
                                                      name="phone"
                                                      type="tel"
                                                      value={phone}
                                                      onChange={this.reservationChange}

                                                      placeholder="Vložte tel. číslo"/>
                                    </InputGroup>
                                </Form.Group>

                                <Form.Group as={Col} controlId="formSpz">
                                    <InputGroup className="my-4">
                                        <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1"
                                                             className="font-weight-bold">SPZ</InputGroup.Text>
                                        </InputGroup.Prepend>

                                        <Form.Control required
                                                      name="spz"
                                                      type="name"
                                                      value={spz}
                                                      onChange={this.reservationChange}
                                                      placeholder="Vložte spz"/>
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
                                                      placeholder="Date"/>
                                    </Form.Group></Col>


                                    <Col>

                                        <Form.Group as={Col} controlId="formTime">

                                            <Form.Control as="select" custom required
                                                          name="time"
                                                          type="time"
                                                          value={time}
                                                          onChange={this.reservationChange}
                                                          label="Time">
                                                {timesList}

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