import React, { Component } from "react";
import Variable from "./Variable";
import axios from "../../../axios";

import MessageBox from '../../../components/UI/MessageBox/MessageBox';

class EditGlobalVar extends Component {
  state = {
    variables: null,
    variablesFromServer: [],
    isMounted: false,
    show:false,
    message:''
  };

  handleEdit = (newValue, id) => {
    // const tempVariables = { ...this.state.variables };
    // for (let i = 0; i < this.state.variables.length; i++) {
    //   if (this.state.variables[i].id === id) {
    //     tempVariables[i].value = newValue;
    //     break;
    //   }
    // }
    // this.setState({ variables: tempVariables });
  }

  // componentDidMount(){
  //   console.log('componentDidMount EditGlobalVar')

  //   let x = null
  //     axios.get('https://cse308-de3df.firebaseio.com/global-variable.json').then(response=>{
  //       x= response.data

  //       if(x!=null){
  //         console.log(x)
  //         let newVariables = []
  //         for(let i in response.data){
  //           newVariables.push(x[i]);

  //         }
  //         console.log('variablesFromServer', newVariables);
  //         this.setState({variablesFromServer : newVariables})
  //       }
  //     });
  // }


    showMessageBox = (message) => {
      this.setState({ show: true , message : message});
    }

    closeMessageBox = () => {
      this.setState({ show: false });
    }

  componentDidMount() {
    console.log(["EditGlobalVar componentDidMount"]);
    //for avoiding update error, I use isMounted value
    this.setState({ isMounted: true }, () => {
      axios
        .get("/sysad/var/view")
        .then(response => {
          const data = response.data;
          const length = data.length;

          let newVariables = [];
          console.log("EditGlobalVar data", data);
          for (let i = 0; i < length; i++) {
            newVariables.push(data[i]);
          }

          console.log("EditGlobalVar newVariables", newVariables);
          if (this.state.isMounted) {
            console.log("EditGlobalVar newVariables", "UPLOADED");
            this.setState({ variables: newVariables });
          }
        })
        .catch(error => {
          console.log(error);
        });
    });
  }

  componentWillUnMount() {
    this.setState({ isMounted: false });
  }

  render() {
    return (
      <div>
        <MessageBox show={this.state.show} modalClosed={this.closeMessageBox} message={this.state.message}/>

        <h1>Global Variables</h1>
        <div className="var-list">
          <div className="row nest">
            <div className="col-sm">
              <h4>Variable Names</h4>
            </div>
            <div className="col-sm">
              <h4>Values</h4>
            </div>
            <div className="col-sm" />
          </div>
        </div>
        {this.state.variables
          ? this.state.variables.map(variable => (
              <Variable
                showMessageBox={this.showMessageBox}
                key={variable._id}
                variable={variable}
                onEdit={this.handleEdit}
              />
            ))
          : null}
      </div>
    );
  }
}

export default EditGlobalVar;
