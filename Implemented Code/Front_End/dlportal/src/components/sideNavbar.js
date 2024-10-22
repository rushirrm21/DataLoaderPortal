import React from "react";
import { Navbar, NavLink, NavItem, NavbarBrand, Nav } from 'reactstrap'

const sideNavbar = () => {
    return (
        <Nav pills style={{ marginTop: "100px" }}>
            <NavItem>
                <NavLink href="/components/">Induction</NavLink>
            </NavItem>
            <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">Update</NavLink>
            </NavItem>
            <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">Process</NavLink>
            </NavItem>
            <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">Logout</NavLink>
            </NavItem>
        </Nav>
    );
}
export default sideNavbar
