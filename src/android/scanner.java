package com.scanner.plugin.mt;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanner extends CordovaPlugin {

	private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

		this.callbackContext = callbackContext;
	
        if (action.equals("scan")) {

            /*String name = data.getString(0);
            String message = "Hello, " + name;
            callbackContext.success(message);*/
			
			IntentIntegrator integrator=new IntentIntegrator(this);
			integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
			integrator.setPrompt("Ubica tu Codigo para Escanear");
			integrator.setCameraId(0);
			integrator.setBeepEnabled(true);
			integrator.setBarcodeImageEnabled(true);
			integrator.setOrientationLocked(true);
			integrator.setCaptureActivity(CaptureActivityPortrait.class);
			integrator.addExtra("TORCH_ON",1);
			integrator.initiateScan();
			
            return true;

        } else {
            
            return false;

        }
    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        //TextView myTexto=(TextView) findViewById(R.id.texto);

        if(result != null) {

            if(result.getContents() == null) {
                //myTexto.setText("cancelado");
				callbackContext.success("cancelado");
            }else{
                //myTexto.setText(result.getContents());
				callbackContext.success(result.getContents());
            }

        } else {

            this.callbackContext.error("error");

        }

    }
	
	
}