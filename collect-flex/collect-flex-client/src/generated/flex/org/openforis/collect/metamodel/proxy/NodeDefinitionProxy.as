/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.metamodel.proxy {
	import mx.collections.IList;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.metamodel.proxy.NodeDefinitionProxy")]
    public class NodeDefinitionProxy extends NodeDefinitionProxyBase {
		
		public function getLabelText(language:String = "en"):String {
			return getLabel(this.labels, NodeLabelProxy$Type.INSTANCE, language);
		}
		
		public static function getLabel(labels:IList, type:NodeLabelProxy$Type, language:String = "en"):String {
			if(labels == null || labels.length <= 0) {
				return null;
			} else if(labels.length == 1) {
				return NodeLabelProxy(labels.getItemAt(0)).text;
			} else {
				for each(var label:NodeLabelProxy in labels) {
					if(label.type == type && label.language == language) {
						return label.text;
					}
				} 
				return (labels.getItemAt(0) as NodeLabelProxy).text;
			}
		}
		
    }
}