import React, { Component } from 'react';
import { connect } from 'react-redux'
import { FormGroup, Label, Col, Dropdown, DropdownMenu, DropdownItem } from 'reactstrap';

import SurveySelect from '../SurveySelect/';

class Header extends Component {

  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      dropdownOpen: false
    };
  }

  toggle() {
    this.setState({
      dropdownOpen: !this.state.dropdownOpen
    });
  }

  sidebarToggle(e) {
    e.preventDefault();
    document.body.classList.toggle('sidebar-hidden');
  }

  sidebarMinimize(e) {
    e.preventDefault();
    document.body.classList.toggle('sidebar-minimized');
  }

  mobileSidebarToggle(e) {
    e.preventDefault();
    document.body.classList.toggle('sidebar-mobile-show');
  }

  asideToggle(e) {
    e.preventDefault();
    document.body.classList.toggle('aside-menu-hidden');
  }

  render() {
    const loggedUser = this.props.loggedUser
    if (loggedUser == null) {
      return <div>Loading...</div>
    }
    return (
      <header className="app-header navbar">
        <button className="navbar-toggler mobile-sidebar-toggler d-lg-none" type="button" onClick={this.mobileSidebarToggle}>&#9776;</button>
        <a className="navbar-brand d-md-down-none" href="#"></a>
        <ul className="nav navbar-nav d-md-down-none">
          <li className="nav-item">
            <button className="nav-link navbar-toggler sidebar-toggler" type="button" onClick={this.sidebarToggle}>&#9776;</button>
          </li>
          <li className="nav-item">
            <FormGroup row>
              <Label sm={4}>Preferred survey: </Label>
              <Col sm={8}>
                <SurveySelect />
              </Col>
            </FormGroup>
          </li>
        </ul>
        
        <ul className="nav navbar-nav ml-auto">
	        <li className="nav-item">
            <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
              <button onClick={this.toggle} className="nav-link dropdown-toggle" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded={this.state.dropdownOpen}>
                <span className="d-md-down-none">{loggedUser.username}</span>
              </button>

              <DropdownMenu className="dropdown-menu-right">
                <DropdownItem header className="text-center"><strong>Account</strong></DropdownItem>
                <DropdownItem><i className="fa fa-user"></i> Change Password</DropdownItem>
                <DropdownItem><i className="fa fa-lock"></i> Logout</DropdownItem>
              </DropdownMenu>
            </Dropdown>
          </li>
          <li className="nav-item d-md-down-none">
            <button className="nav-link navbar-toggler aside-menu-toggler" type="button" onClick={this.asideToggle}>&#9776;</button>
          </li>
        </ul>
      </header>
    )
  }
}
const mapStateToProps = state => {
  const {
      loggedUser
  } = state.session

  return {
      loggedUser
  }
}
export default connect(mapStateToProps)(Header);