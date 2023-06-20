import './App.css';
import Navigation from "./navigation/Navigation";
import Home from "./components/Home";
import * as ReactRouterDOM from "react-router-dom";
import CompetitionPage from "./components/CompetitionPage";
import Login from "./components/Login";
import Registration from "./components/Registration";
import AdminPage from "./components/AdminPage";

function App() {
    const Router = ReactRouterDOM.BrowserRouter;
    const Route = ReactRouterDOM.Route;
    const Routes = ReactRouterDOM.Routes;

    return (
        <Router>
            <div>
                <Navigation/>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/competitions/:id" element={<CompetitionPage/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Registration/>}/>
                    <Route path="/admin" element={<AdminPage/>}/>
                </Routes>
            </div>
        </Router>
    );
}

export default App;
