import { Component } from 'react'
import React from 'react'
import { Navbar, NavLink, NavItem, NavbarBrand,Nav } from 'reactstrap'

class HeaderComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <header className="headerr">         
                        <Nav pills>
                        <NavbarBrand><h1>Data Loader Portal</h1></NavbarBrand>
                        </Nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent