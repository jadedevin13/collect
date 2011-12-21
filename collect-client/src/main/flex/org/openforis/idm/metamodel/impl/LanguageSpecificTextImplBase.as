/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (LanguageSpecificTextImpl.as).
 */

package org.openforis.idm.metamodel.impl {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.idm.metamodel.LanguageSpecificText;

    [Bindable]
    public class LanguageSpecificTextImplBase implements IExternalizable, LanguageSpecificText {

        private var _language:String;
        private var _text:String;

        [Bindable(event="unused")]
        public function get language():String {
            return _language;
        }

        [Bindable(event="unused")]
        public function get text():String {
            return _text;
        }

        public function readExternal(input:IDataInput):void {
            _language = input.readObject() as String;
            _text = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_language);
            output.writeObject(_text);
        }

		public function set text(value:String):void {
			_text = value;
		}

		public function set language(value:String):void {
			_language = value;
		}


    }
}