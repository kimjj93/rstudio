package org.rstudio.core.client.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface WizardResources extends ClientBundle
{
   interface Styles extends CssResource
   {
      String mainWidget();
      String headerLabel();
      String wizardBodyPanel();
      String wizardPageSelector();
      String wizardPageSelectorItem();
   }
   
   @Source("Wizard.css")
   Styles styles(); 
   
   ImageResource wizardBackButton();
   ImageResource wizardDisclosureArrow();
   
   @Source("wizardListInnerShadow.png")
   DataResource wizardListInnerShadow();
   
   
   
   static WizardResources INSTANCE = 
                        (WizardResources)GWT.create(WizardResources.class);
}
