import React,{Component} from 'react';
import Dropdown from 'react-dropdown';
import classes from './SignInBody.module.css';

class SignInBody extends Component{
	
	state={
		options : ['Manager', 'Canvasser', 'System Admin'],
		userID : null,
		password : null,
		role : null
	}
	
	render(){
		return (
			<div className={[classes.SignInBody, "container", "text-center"].join(' ')}> 
				
				<h1 className = {classes.Title}>Sign In </h1>

				<form className={"form-signin"}>
					<div className= {[classes.InputTextWrapper,"text-center","form-group"].join(' ')}>
						<input 
							className = {[classes.FormControl].join(' ')}
							placeholder = "User ID" 
							onChange={(event) => this.setState({userID: event.target.value})}/>
					</div>
					
					<div className= {[classes.InputTextWrapper,"text-center","form-group"].join(' ')}>
						<input 
							className = {[classes.FormControl].join(' ')}
							placeholder = "password"
							onChange={(event) => this.setState({password: event.target.value})}/>
					</div>
					
					<div className = {"form-group"}>
						<Dropdown 
						 placeholderClassName ="dropdown-toggle"
						 className = {[classes.Dropdown].join(' ')}
					     options = {this.state.options}
					     placeholder="Role"
					     value={this.state.role?this.state.role:'Role'}
						 onChange={(event) => this.setState({role:event.value})}/>
					 </div>
					
					<input className = {["btn","btn-dark", classes.SignInBtn].join(' ')} type="submit" value = "Sign In"/>
				</form>
			</div>

		);
	}
}

export default SignInBody;