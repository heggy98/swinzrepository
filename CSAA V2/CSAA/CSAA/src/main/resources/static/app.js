'use strict';

const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>

class App extends React.Component { // <1>

    constructor(props) {
        super(props);
        this.state = {reservations: []};
    }

    componentDidMount() { // <2>
        client({method: 'GET', path: '/api/reservations'}).done(response => {
            this.setState({reservations: response.entity._embedded.reservations});
        });
    }

    render() { // <3>
        return (
            <ReservationList reservations={this.state.reservations}/>
        )
    }
}

class ReservationList extends React.Component{
    render() {
        const reservations = this.props.reservations.map(reservation =>
            <Reservation key={reservation._links.self.href} data={reservation}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>PHONE</th>
                    <th>SPZ</th>
                    <th>TIME INDEX</th>
                    <th>DATE</th>
                </tr>
                {reservations}
                </tbody>
            </table>
        )
    }
}

class Reservation extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.reservation.id}</td>
                <td>{this.props.reservation.name}</td>
                <td>{this.props.reservation.phone}</td>
                <td>{this.props.reservation.spz}</td>
                <td>{this.props.reservation.timeIndex}</td>
                <td>{this.props.reservation.date}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
