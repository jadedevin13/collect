/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (ComparisonCheckImpl.as).
 */

package org.openforis.idm.metamodel.impl {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import org.openforis.idm.metamodel.ComparisonCheck;

    [Bindable]
    public class ComparisonCheckImplBase extends AbstractCheck implements ComparisonCheck {

        private var _equalsExpression:String;
        private var _greaterThanExpression:String;
        private var _greaterThanOrEqualsExpression:String;
        private var _lessThanExpression:String;
        private var _lessThanOrEqualsExpression:String;

        [Bindable(event="unused")]
        public function get equalsExpression():String {
            return _equalsExpression;
        }

        [Bindable(event="unused")]
        public function get greaterThanExpression():String {
            return _greaterThanExpression;
        }

        [Bindable(event="unused")]
        public function get greaterThanOrEqualsExpression():String {
            return _greaterThanOrEqualsExpression;
        }

        [Bindable(event="unused")]
        public function get lessThanExpression():String {
            return _lessThanExpression;
        }

        [Bindable(event="unused")]
        public function get lessThanOrEqualsExpression():String {
            return _lessThanOrEqualsExpression;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _equalsExpression = input.readObject() as String;
            _greaterThanExpression = input.readObject() as String;
            _greaterThanOrEqualsExpression = input.readObject() as String;
            _lessThanExpression = input.readObject() as String;
            _lessThanOrEqualsExpression = input.readObject() as String;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_equalsExpression);
            output.writeObject(_greaterThanExpression);
            output.writeObject(_greaterThanOrEqualsExpression);
            output.writeObject(_lessThanExpression);
            output.writeObject(_lessThanOrEqualsExpression);
        }
    }
}