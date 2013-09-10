/*******************************************************************************
* Copyright 2013 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.galen.components.validation;

import net.mindengine.galen.specs.Spec;
import net.mindengine.galen.validation.PageValidation;
import net.mindengine.galen.validation.ValidationError;
import net.mindengine.galen.validation.ValidationListener;

public class TestValidationListener implements ValidationListener {

    private StringBuffer invokations = new StringBuffer();

    @Override
    public void onSpecError(PageValidation pageValidation, String objectName, Spec spec, ValidationError error) {
        append("<" + spec.getClass().getSimpleName() + " " + objectName + ">");
        StringBuffer buffer = new StringBuffer();
        for (String message : error.getMessages()) {
            buffer.append("<msg>");
            buffer.append(message);
            buffer.append("</msg>");
        }
        append("<e>" + buffer.toString() + "</e>");
    }

    @Override
    public void onSpecSuccess(PageValidation pageValidation, String objectName, Spec spec) {
        append("<" + spec.getClass().getSimpleName() + " " + objectName + ">");
    }

    @Override
    public void onObject(PageValidation pageValidation, String objectName) {
        append("<o " + objectName + ">");
    }

    private void append(String text) {
        invokations.append(text);
        invokations.append('\n');
    }
    
    public String getInvokations() {
        return invokations.toString();
    }

    @Override
    public void onAfterObject(PageValidation pageValidation, String objectName) {
        append("</o " + objectName + ">");
    }

}