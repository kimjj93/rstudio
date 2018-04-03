/*
 * AboutDialog.java
 *
 * Copyright (C) 2009-18 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

package org.rstudio.studio.client.application.ui;
import org.rstudio.core.client.widget.ModalDialogBase;
import org.rstudio.core.client.widget.ThemedButton;
import org.rstudio.studio.client.RStudioGinjector;
import org.rstudio.studio.client.application.Desktop;
import org.rstudio.studio.client.application.model.ProductEditionInfo;
import org.rstudio.studio.client.application.model.ProductInfo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class AboutDialog extends ModalDialogBase
{
   public AboutDialog(ProductInfo info)
   {
      RStudioGinjector.INSTANCE.injectMembers(this);

      info_ = info;
      setText("About " + editionInfo_.editionName());
      ThemedButton OKButton = new ThemedButton("OK", (ClickEvent) -> closeDialog());
      addOkButton(OKButton);
      
      if (editionInfo_.proLicense() && Desktop.isDesktop())
      {
         ThemedButton licenseButton = new ThemedButton("Manage License...", (ClickEvent) ->  {
            closeDialog();
            editionInfo_.showLicense();
         });
         addLeftButton(licenseButton);
      }
      contents_ = new AboutDialogContents(info_, editionInfo_);
      setWidth("600px");
      firstTimeShown_ = true;
   }

   @Override
   protected Widget createMainWidget()
   {
      return contents_;
   }
   
   @Override
   protected void onDialogShown()
   {
      if (!firstTimeShown_ && editionInfo_.proLicense() && Desktop.isDesktop())
      {
         // update license status display every time About dialog is shown
         contents_ = new AboutDialogContents(info_, editionInfo_);
      }
      firstTimeShown_ = false;
      super.onDialogShown();
   }

   @Inject
   private void initialize(ProductEditionInfo editionInfo)
   {
      editionInfo_ = editionInfo;
   } 
   
   private boolean firstTimeShown_;
   private ProductInfo info_;
   private AboutDialogContents contents_;
   private ProductEditionInfo editionInfo_;
}
