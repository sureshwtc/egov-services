import _ from 'lodash';
import {translate} from '../components/common/common';

const defaultState = {
    form: {},
    files: [],
    msg: '',
    toastMsg: '',
    dialogOpen: false,
    snackbarOpen: false,
    fieldErrors: {},
    requiredFields: [],
    isFormValid: true,
    showTable: false,
    buttonText: "Search",
    editIndex: -1
};

export default (state = defaultState, action) => {
    switch (action.type) {
        case "SET_FORM_DATA":
            
            return {
                ...state,
                form: action.data
            }

        case "SET_REQUIRED_FIELDS":
            return {
              ...state,
              requiredFields: action.requiredFields,
              fieldErrors: {},
              isFormValid: action.requiredFields.length == 0 ? true : false
            }
        case "HANDLE_CHANGE_FRAMEWORK":
            var currentState = { ...state };
            _.set(currentState.form, action.property, action.value);
            var validationDat = validate(currentState.fieldErrors, action.property, action.value, action.isRequired, currentState.form, currentState.requiredFields, action.pattern, action.patternErrMsg);
            //Set field errors
            currentState.fieldErrors = {
              ...state.fieldErrors,
              [action.property]: validationDat.errorText
            };
            //Set form valid or not
            currentState.isFormValid = validationDat.isFormValid;
            return currentState;

        case "RESET_STATE":
            return {
                ...defaultState
            }
        case 'FIELD_ERRORS':
            return {
                ...state,
                fieldErrors: action.errors
            }

        case "SHOW_TABLE":
            return {
                ...state,
                showTable: action.state
            }
        case "BUTTON_TEXT":
            return {
                ...state,
                buttonText: action.text
            }
        case "TOGGLE_DAILOG_AND_SET_TEXT":
            return {
                ...state,
                msg: action.msg,
                dialogOpen: action.dailogState,
            }
        case "TOGGLE_SNACKBAR_AND_SET_TEXT":
            return {
                ...state,
                toastMsg: action.toastMsg,
                snackbarOpen: action.snackbarState,
            }
        case "SET_LOADING_STATUS":
            return {
                ...state,
                loadingStatus: action.loadingStatus
            }
        default:
            return state;
    }
}

function validate(fieldErrors, property, value, isRequired, form, requiredFields, pattern, patErrMsg) {
  let errorText = isRequired && !value ? translate('ui.framework.required') : '';
  let isFormValid = true;
  for(var i=0; i<requiredFields.length; i++) {
    if(!_.get(form, requiredFields[i])) {
      isFormValid = false;
      break;
    }
  }

  if(pattern && _.get(form, property) && !new RegExp(pattern).test(_.get(form, property))) {
    errorText = patErrMsg ? translate(patErrMsg) : translate('ui.framework.patternMessage');
    isFormValid = false;
  }

  for(let key in fieldErrors) {
    if(fieldErrors[key]) {
        isFormValid = false;
        break;
    }
  }


  return {
    isFormValid,
    errorText
  }
}