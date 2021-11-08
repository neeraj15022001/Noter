package com.example.noter

import android.app.job.JobInfo
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noter.firebase.service.FireBaseSyncService
import android.app.job.JobScheduler
import android.util.Log


class MainActivity : AppCompatActivity() {
    var FIREBASE_JOB_ID = 101
    var TAG = MainActivity::class.java.name
    lateinit var jobScheduler:JobScheduler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start the job in the beginning it self
        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        startJob()
        // job end code
    }

    private fun startJob() {
        var componentName = ComponentName(this, FireBaseSyncService::class.java)
        var jobInfo = JobInfo.Builder(FIREBASE_JOB_ID, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPeriodic(1000*60*60)
            .setRequiresCharging(false)
            .setPersisted(true)
            .build()
    
        if (!isJobServiceOn()) {
            if(jobScheduler.schedule(jobInfo).equals(JobScheduler.RESULT_SUCCESS)) {
                Log.i(TAG, "startJob: Job scheduled")
            }
            else {
                Log.i(TAG, "startJob: job could not be scheduled due to some issue")
            }
        }
        else {
            Log.i(TAG, "startJob: not scheduling job because it is already scheduled")
        }
    }

    fun isJobServiceOn(): Boolean {
        var hasBeenScheduled = false
        for (jobInfo in jobScheduler.allPendingJobs) {
            if (jobInfo.id == FIREBASE_JOB_ID) {
                hasBeenScheduled = true
                break
            }
        }
        return hasBeenScheduled
    }
}