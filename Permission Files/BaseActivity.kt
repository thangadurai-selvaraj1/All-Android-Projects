  @SuppressLint("InflateParams")
    fun showGoToSettingsDialog(
        context: Context,
        body: String
    ) {
        val builder = AlertDialog.Builder(this)
        val dialog = layoutInflater.inflate(R.layout.dialog_go_to_settings, null)
        val alertDialog = builder.create()

        dialog.findViewById<TextView>(R.id.tv_go_to_settings).text = body

        dialog.findViewById<Button>(R.id.btn_go_to_settings)
            .setOnClickListener {
                alertDialog?.dismiss()
                AskPermission.launchSettingsScreen(context)
            }


        alertDialog?.setView(dialog)
        alertDialog?.show()
    }