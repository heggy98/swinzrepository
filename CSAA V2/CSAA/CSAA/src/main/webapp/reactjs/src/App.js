import logo from './logo.svg';
import './App.css';
import NavigationBar from "./components/NavigationBar";
import {Container, Row, Col} from "react-bootstrap";
import Reservation from "./components/Reservation"
import ReservationList from "./components/ReservationList"
import Welcome from "./components/Welcome";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';


function App() {
  return (
    <Router className="App">
      <NavigationBar/>
      <br/>
      <Container>
            <Row>
                <Col lg={12}>
                    <Switch>
                        <Route path="/" exact component={Welcome}/>
                        <Route path="/add" exact component={Reservation}/>
                        <Route path="/edit/:id" exact component={Reservation}/>
                        <Route path="/list" exact component={ReservationList}/>
                    </Switch>
                </Col>
            </Row>
      </Container>
    </Router>
  );
}

export default App;
