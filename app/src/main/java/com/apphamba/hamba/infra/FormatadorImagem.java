package com.apphamba.hamba.infra;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;

public class FormatadorImagem {

    private Bitmap gerarBitmMap(int id){
        Bitmap bitmap = BitmapFactory.decodeResource(HambaApp.getContext().getResources(), id , new BitmapFactory.Options());
        return  bitmap;
    }

    private byte[] bitMapParaByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imagemBytes[] = stream.toByteArray();
        return imagemBytes;
    }

    public byte[] gerarFoto(int id){
        byte[] fotoByte = this.bitMapParaByte(gerarBitmMap(id));
        return fotoByte;
    }
}
