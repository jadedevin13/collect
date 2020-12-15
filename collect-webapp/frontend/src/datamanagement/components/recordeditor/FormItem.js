import React, { useState } from 'react'
import { Row, Col } from 'reactstrap'
import classNames from 'classnames'

import { FieldDefinition } from 'model/ui/FieldDefinition'
import { CodeAttributeDefinition } from 'model/Survey'
import FormItemTypes from 'model/ui/FormItemTypes'
import * as Validations from 'model/Validations'
import { NodeCountUpdatedEvent, NodeCountValidationUpdatedEvent } from 'model/event/RecordEvent'

import ValidationTooltip from 'common/components/ValidationTooltip'
import { useRecordEvent } from 'common/hooks'
import Fieldset from './Fieldset'
import MultipleFieldset from './MultipleFieldset'
import Table from './Table'
import FormItemFieldComponent from './FormItemFieldComponent'
import FormItemMultipleFieldComponent from './FormItemMultipleFieldComponent'
import NodeDefLabel from './NodeDefLabel'

const internalComponentByFieldType = {
  [FormItemTypes.FIELD]: FormItemFieldComponent,
  [FormItemTypes.MULTIPLE_FIELD]: FormItemMultipleFieldComponent,
  [FormItemTypes.MULTIPLE_FIELDSET]: MultipleFieldset,
  [FormItemTypes.FIELDSET]: Fieldset,
  [FormItemTypes.TABLE]: Table,
}

const _includeLabel = (itemDef) => itemDef instanceof FieldDefinition

const FormItem = (props) => {
  const { itemDef, parentEntity, fullSize } = props
  const { nodeDefinition, type } = itemDef
  const { id: nodeDefinitionId } = nodeDefinition

  const [cardinalityValidation, setCardinalityValidation] = useState(
    Validations.getCardinalityValidation({ nodeDefinition, parentEntity })
  )

  useRecordEvent({
    parentEntity,
    onEvent: (event) => {
      if (
        (event instanceof NodeCountValidationUpdatedEvent || event instanceof NodeCountUpdatedEvent) &&
        event.isRelativeToNodes({ parentEntity, nodeDefId: nodeDefinitionId })
      ) {
        setCardinalityValidation(Validations.getCardinalityValidation({ nodeDefinition, parentEntity }))
      }
    },
  })

  const wrapperId = `form-item-${parentEntity.id}-node-def-${nodeDefinitionId}`

  const InternalComponentClass =
    nodeDefinition instanceof CodeAttributeDefinition ? FormItemFieldComponent : internalComponentByFieldType[type]

  const internalComponent = <InternalComponentClass itemDef={itemDef} parentEntity={parentEntity} fullSize={fullSize} />

  return fullSize ? (
    internalComponent
  ) : (
    <Row>
      {_includeLabel(itemDef) && (
        <Col style={{ width: '200px', maxWidth: '200px' }}>
          <NodeDefLabel nodeDefinition={nodeDefinition} />
        </Col>
      )}
      <Col>
        <>
          <div id={wrapperId} className={classNames('form-item-wrapper', { error: cardinalityValidation.hasErrors() })}>
            {internalComponent}
          </div>
          <ValidationTooltip target={wrapperId} validation={cardinalityValidation} />
        </>
      </Col>
    </Row>
  )
}

FormItem.defaultProps = {
  fullSize: false,
}

export default FormItem
