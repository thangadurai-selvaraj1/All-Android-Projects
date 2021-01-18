splashBinding.vv.setOnClickListener {

            if (AskPermission.checkVersion()) {
                if (AskPermission.checkPermission(
                        this, BOTH_CAMERA_AND_STORAGE
                    )
                ) {
                    showShortMessage(this, "Task Executed")
                } else {
                    AskPermission.requestPermission(this, BOTH_CAMERA_AND_STORAGE)
                }


            } else {
                showShortMessage(this, "Lower Version")
            }


        }
		
		


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (AskPermission.handlePermissionsResult(requestCode, permissions, grantResults)) {
            showShortMessage(this, "Task Executed")
        } else {
            if (!AskPermission.shouldShowRequestPermissionRationale(
                    this,
                    BOTH_CAMERA_AND_STORAGE
                )
            ) {
                showGoToSettingsDialog(this, AskPermission.ALREADY_PERMISSION_DENY_TEXT)
            } else {
                showShortMessage(this, "Pls Enable Permission")
            }
        }
    }