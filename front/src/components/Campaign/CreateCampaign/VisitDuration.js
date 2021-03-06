import React, {Component} from 'react'
import classes from './CreateCampaign.module.css'

class VisitDuration extends Component{
	

	render(){
		return(
			<div className={[classes.Section, 'row'].join(' ')}>
				<div className='col-3 text-center'> 
					<p>Visit Duration</p>
				</div>
				
				
				<div className = {['col-3', classes.InputSection].join(' ')}>
					<input 	
							name = 'visitMin'
							pattern="[0-9]*"
							value = {this.props.visitMin}
							className = {[classes.TextField].join(' ')}
							placeholder = 'Min'
							onChange={this.props.onChange}/>
				</div>
			</div>
		);
	}

}

export default VisitDuration;