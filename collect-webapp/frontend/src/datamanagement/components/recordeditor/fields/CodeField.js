import React from 'react'
import { Label, Input, FormGroup } from 'reactstrap'
import ServiceFactory from 'services/ServiceFactory'
import AbstractField from './AbstractField'

export default class CodeField extends AbstractField {
  constructor(props) {
    super(props)

    this.state = {
      ...this.state,
      value: { code: '' },
      items: [],
    }

    this.handleInputChange = this.handleInputChange.bind(this)
  }

  componentDidMount() {
    this.handleParentEntityChanged()
  }

  componentDidUpdate(prevProps) {
    const { parentEntity } = this.props
    const { parentEntity: prevParentEntity } = prevProps
    if (prevParentEntity !== parentEntity) {
      this.handleParentEntityChanged()
    }
  }

  extractValueFromProps() {
    const attr = this.getSingleAttribute()
    return this.fromCodeToValue(attr.fields[0].value)
  }

  fromCodeToValue(code) {
    let codeUpdated = null
    if (code === null) {
      const layout = this.props.fieldDef.layout
      codeUpdated = layout === 'DROPDOWN' ? '-1' : ''
    } else {
      codeUpdated = code
    }
    return { code: codeUpdated }
  }

  handleParentEntityChanged() {
    const { parentEntity } = this.props

    if (parentEntity) {
      this.loadCodeListItems(parentEntity)
      const value = this.extractValueFromProps()
      this.setState({ value })
    }
  }

  loadCodeListItems(parentEntity) {
    const attr = this.getSingleAttribute(parentEntity)
    if (attr) {
      ServiceFactory.codeListService
        .findAvailableItems(parentEntity, attr.definition)
        .then((items) => this.setState({ items: items }))
    }
  }

  extractValueFromAttributeUpdateEvent(event) {
    return this.fromCodeToValue(event.code)
  }

  handleInputChange(event) {
    const { fieldDef } = this.props
    const { layout } = fieldDef
    const debounced = layout === 'TEXT'
    this.onAttributeUpdate({ value: this.fromCodeToValue(event.target.value), debounced })
  }

  render() {
    const { fieldDef, parentEntity } = this.props
    const { items, value } = this.state
    const { code } = value

    if (!parentEntity || !fieldDef) {
      return <div>Loading...</div>
    }

    const attrDef = fieldDef.attributeDefinition
    const layout = fieldDef.layout

    switch (layout) {
      case 'DROPDOWN':
        const EMPTY_OPTION = (
          <option key="-1" value="-1">
            --- Select one ---
          </option>
        )
        return (
          <Input type="select" onChange={this.handleInputChange} value={code}>
            {[EMPTY_OPTION].concat(
              items.map((item) => (
                <option key={item.code} value={item.code}>
                  {item.label}
                </option>
              ))
            )}
          </Input>
        )
      case 'RADIO':
        return (
          <div>
            {items.map((item) => (
              <FormGroup check key={item.code}>
                <Label check>
                  <Input
                    type="radio"
                    name={'code_group_' + parentEntity.id + '_' + attrDef.id}
                    value={item.code}
                    checked={item.code === code}
                    onChange={this.handleInputChange}
                  />{' '}
                  {item.label}
                </Label>
              </FormGroup>
            ))}
          </div>
        )
      default:
        return <Input value={code} onChange={this.handleInputChange} style={{ maxWidth: '100px' }} />
    }
  }
}
