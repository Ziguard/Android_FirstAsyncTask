package com.example.gef.myfirstthreadasynctasks;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class MyAsyncTask extends AsyncTask<Long , Float, Boolean>
{
    WeakReference<MainActivity> weakActivity = null;


    public MyAsyncTask(MainActivity activity)
    {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Boolean doInBackground(Long... nombres) {
        if (nombres.length >0)
        {
            long nombre = nombres[0];

            long diviseurMax = (long) Math.ceil(Math.sqrt(nombre));
            for(long a  = 2; a<= diviseurMax; a++ )
            {
                if(nombre % a == 0){
                    return false;
                }

                if(a % 10000 == 0){
                    publishProgress((float) (double) a / diviseurMax);
                }
            }
            return true;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Float... values)
    {
        if (weakActivity.get() != null)
        {
            weakActivity.get().updateProgression(values[0]);
        }
    }
    @Override
    protected void onPostExecute(Boolean resultat) {
        if (weakActivity.get() != null)
        {
            weakActivity.get().afficherResultat(resultat);
        }
    }
}
