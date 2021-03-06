import React from 'react'
import { connect } from 'react-redux'
import classNames from 'classnames'
import { MuiPickersUtilsProvider, KeyboardDatePicker } from '@material-ui/pickers'
import DateFnsUtils from '@date-io/date-fns'

import Dates from 'utils/Dates'
import L from 'utils/Labels'

import AbstractField from './AbstractField'
import * as FieldSizes from './FieldsSizes'
import DirtyFieldSpinner from './DirtyFieldSpinner'

const fromValueToDate = (value) => (value ? new Date(value.year, value.month - 1, value.day) : null)
const fromDateToValue = (date) => {
  const dateUtils = new DateFnsUtils()
  return {
    year: dateUtils.getYear(date),
    month: dateUtils.getMonth(date) + 1,
    day: Number(dateUtils.getDayText(date)),
  }
}

class DateField extends AbstractField {
  constructor() {
    super()
    this.datePickerWrapperRef = React.createRef()
    this.onChange = this.onChange.bind(this)
  }

  onChange(date) {
    const incomplete = isNaN(date)

    this.handleIncompleteFeedback(incomplete)

    if (incomplete) {
      return
    }
    const value = date === null ? null : fromDateToValue(date)
    this.updateValue({ value })
  }

  handleIncompleteFeedback(incomplete) {
    const wrapper = this.datePickerWrapperRef.current
    wrapper.className = classNames('date-picker-wrapper', { error: incomplete })
    wrapper.title = incomplete ? L.l('dataManagement.dataEntry.attribute.date.incompleteDate') : ''
  }

  render() {
    const { fieldDef, inTable, parentEntity, user } = this.props
    const { dirty, value: valueState } = this.state
    const { record } = parentEntity
    const { attributeDefinition } = fieldDef

    const readOnly = !user.canEditRecordAttribute({ record, attributeDefinition })

    const selectedDate = fromValueToDate(valueState)

    return (
      <div ref={this.datePickerWrapperRef}>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            variant="dialog"
            inputVariant="outlined"
            format={Dates.DATE_FORMAT}
            margin="none"
            minDate={null}
            maxDate={null}
            value={selectedDate}
            disabled={readOnly}
            onChange={this.onChange}
            style={{ width: `${FieldSizes.getWidth({ fieldDef, inTable })}px` }}
          />
        </MuiPickersUtilsProvider>
        {dirty && <DirtyFieldSpinner />}
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  const { session } = state
  const { loggedUser: user } = session
  return { user }
}

export default connect(mapStateToProps)(DateField)
