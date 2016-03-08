package com.team.qr;

import android.content.DialogInterface;
import android.graphics.PointF;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class MainActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView QRDecoderView;
    private AlertDialog mAlertDialog;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        QRDecoderView = (QRCodeReaderView) findViewById(R.id.v_qr);
        QRDecoderView.setOnQRCodeReadListener(this);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            //TODO action if already Dialog is showing
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("QR Code")
                    .setMessage(text)
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.dismiss();
                        }
                    });
            mAlertDialog = builder.create();
            mAlertDialog.show();
        }
    }

    @Override
    public void cameraNotFound() {
        Toast.makeText(this,"no camera found",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
        Toast.makeText(this,"no QR code found",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume () {
        super.onResume();
        QRDecoderView.getCameraManager().startPreview();
    }

    @Override
    protected void onPause () {
        super.onPause();
        QRDecoderView.getCameraManager().stopPreview();
    }
}