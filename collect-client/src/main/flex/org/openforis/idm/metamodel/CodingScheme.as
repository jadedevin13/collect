/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR.
 */

package org.openforis.idm.metamodel {

    import mx.collections.ListCollectionView;

    public interface CodingScheme {

        function get codeScope():CodingScheme$CodeScope;

        function get codeType():CodingScheme$CodeType;

        function get default():Boolean;

        function get descriptions():ListCollectionView;

        function get labels():ListCollectionView;

        function get name():String;
    }
}