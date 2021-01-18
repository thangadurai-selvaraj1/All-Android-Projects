 fun showSuccessCustomToast(msg: String) {
        CustomToast.makeText(this, msg, 2000, CustomToast.SUCCESS).show()
    }

    fun showFailureCustomToast(msg: String) {
        CustomToast.makeText(this, msg, 2000, CustomToast.ERROR).show()
    }

    fun showWarningCustomToast(msg: String) {
        CustomToast.makeText(this, msg, 2000, CustomToast.WARNING).show()
    }

    fun showInfoCustomToast(msg: String) {
        CustomToast.makeText(this, msg, 2000, CustomToast.INFO).show()
    }

