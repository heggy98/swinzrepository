import React, {Component} from 'react';
import {Row, Container, Button, Card, Col, Form, InputGroup, FormControl} from "react-bootstrap";
import axios from 'axios';

export default class ReservationList extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.reservationChange = this.reservationChange.bind(this);
        this.submitReservation = this.submitReservation.bind(this);
    }

    initialState = {
        id:'', name: '', spz: '', phone: '', date: '', time: '', times: []
    }

    componentDidMount() {

        this.getTimeIndexes();

        const reservationId = +this.props.match.params.id;
        if(reservationId){
            this.findReservationById(reservationId);
        }
    }

    findReservationById = (reservationId) => {
        axios.get("http://localhost:8080/reservations/"+reservationId)
            .then(response =>{
                if(response.data != null){
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        spz: response.data.spz,
                        phone: response.data.phone,
                        date: response.data.date,
                        time: response.data.time,
                        timeIndex: response.data.timeIndex



                    });
                }
            }).catch((error) => {
            console.error("Error - "+error);
        });
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
                    this.setState(this.initialState);
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



    reservationChange(event) {
        this.setState({
            [event.target.name]: event.target.value
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



    nameNumberCheck(event){
        var name = event.target.value;
       if (/\d/.test(name)){
           alert("Jméno obsahuje číslice!");
       }
    }


    phoneCheck(event){
        var phone = event.target.value;
        var phoneno = /^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/g;
        if (phone.match(phoneno)){return true;}
        else{
            alert("Telefonní číslo obsahuje nepovolené znaky!");
        }
    }

    updateReservation = event => {
        event.preventDefault();

        const reservation = {
            id: this.state.id,
            name: this.state.name,
            phone: this.state.phone,
            spz: this.state.spz,
            date: this.state.date,
            time: this.state.time,
            timeIndex: this.state.timeIndex

        };

        axios.put("http://localhost:8080/reservations", reservation)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show": true});
                    this.setState(this.initialState);
                    alert("Reservation edited");
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
    };

    reservationList = () =>{
        return this.props.history.push("/list");
    };



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
                    <Card.Header className="text-center text-uppercase font-weight-bold">{this.state.id ? "Upravit " : "Přidat "}
                        rezervaci</Card.Header>
                    <Form onSubmit={this.state.id ? this.updateReservation : this.submitReservation} id="bookFormId">
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
                                                      maxLength={30}
                                                      onChange={this.reservationChange}
                                                      onBlur={this.nameNumberCheck}
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
                                                      maxLength={13}
                                                      onChange={this.reservationChange}
                                                      onBlur={this.phoneCheck}
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
                                                      maxLength={8}
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
                                {this.state.id ? "Upravit" : "Rezervovat"}
                            </Button>
                            <Button variant="secondary mx-3 " type="button" onClick={this.reservationList.bind()}>
                                Seznam
                            </Button>
                        </Container>
                        </Card.Body>
                    </Form>
                </Card></Row>
        );
    }

}