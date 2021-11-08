package com.example.noter.firebase.service;

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

/**
 *
 */
class FireBaseSyncService : JobService() {

    private val TAG = FireBaseSyncService::class.simpleName

    /**
     * Implement this for the firebase sync
     */
    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.i(TAG, "onStartJob: Start the sync job for the firebase")
        Thread.sleep(1000)
        return true;
    }

    /**
     * Implement some logs for job cancellation
     */
    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.i(TAG, "onStartJob: Job completed")
        return true
    }
}