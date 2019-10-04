package onoffrice.weatherhelp.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import onoffrice.weatherhelp.utils.widgets.dialog.DefaultErrorDialog
import onoffrice.weatherhelp.R
import java.io.Serializable

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}

fun Context.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.isNetworkConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun Activity.startActivitySlideTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_close_scale, R.anim.slide_in_left, 1, requestCode)
}

fun Activity.startActivityFadeTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_fade_out, R.anim.anim_fade_in, 1, requestCode)
}

fun Activity.startActivityTransition(intent: Intent, idAnimationOut: Int,
                                     idAnimationIn: Int, delay: Long, requestCode: Int? = null) {
    if (requestCode == null) {
        Handler().postDelayed({
            this.startActivity(intent)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    } else {
        Handler().postDelayed({
            this.startActivityForResult(intent, requestCode)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    }
}

fun Activity.finishWithSlideTransition() {
    finish()
    overridePendingTransition(R.anim.anim_open_scale, R.anim.slide_out_right)
}

fun Activity.finishWithFadeTransition() {
    finish()
    overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
}

fun Activity.finishWithTransition(idAnimationOut: Int, idAnimationIn: Int, delay: Long) {
    Handler().postDelayed({
        this.finish()
        this.overridePendingTransition(idAnimationIn, idAnimationOut)
    }, delay)
}

fun <T : Serializable> Activity.getSerializable(key: String): T {
    return intent.getSerializableExtra(key) as T
}

//TOAST METHODS
fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Context.showErrorToast(msg: String?) {
    showLongToast(msg ?: getString(R.string.error))
}

fun Context.openVideoPlayer(video: String?){
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(video), "video/*")
    startActivity(intent)
}

fun Context.externalShare(content: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content)
        type = "text/plain"
    }
    startActivity(sendIntent)
}

fun Context.openLocationInExternalApps(latitude: Double, longitude: Double, title: String = "Selecione um aplicativo") {
    val  url = "waze://?ll=$latitude,$longitude&navigate=yes";
    val  intentWaze = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intentWaze.setPackage("com.waze")

    val uriGoogle = "google.navigation:q=$latitude,$longitude"
    val intentGoogleNav = Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle))
    intentGoogleNav.setPackage("com.google.android.apps.maps")

    val chooserIntent = Intent.createChooser(intentGoogleNav, title)
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentWaze))

    startActivity(chooserIntent)
}

fun Context.displayDefaultErrorDialog(
        icon: Int? = null,
        title: Int? = null,
        body: Int? = null,
        leftBtnText: Int? = null,
        leftAction: (() -> Unit)? = null,
        rightBtnText: Int? = null,
        rightAction: (() -> Unit)? = null,
        isCancelable: Boolean = true,
        dismissAction: (() -> Unit)? = null
): DefaultErrorDialog =
    displayDefaultErrorDialog(
            icon = icon,
            title = title,
            body = if(body != null) getString(body) else null,
            leftBtnText = leftBtnText,
            leftAction = leftAction,
            rightBtnText = rightBtnText,
            rightAction = rightAction,
            isCancelable = isCancelable,
            dismissAction = dismissAction
    )


fun Context.displayDefaultErrorDialog(
        icon: Int? = null,
        title: Int? = null,
        body: String? = null,
        leftBtnText: Int? = null,
        leftAction: (() -> Unit)? = null,
        rightBtnText: Int? = null,
        rightAction: (() -> Unit)? = null,
        isCancelable: Boolean = true,
        dismissAction: (() -> Unit)? = null
): DefaultErrorDialog {
    val dialog = DefaultErrorDialog(
            context = this,
            icon =  icon ?: R.drawable.ic_warning,
            title = if(title != null) getString(title) else getString(R.string.common_error_title),
            body = body,
            leftBtnText = if(leftBtnText != null) getString(leftBtnText) else null,
            leftAction = leftAction,
            rightBtnText = if(rightBtnText != null) getString(rightBtnText) else null,
            rightAction = rightAction,
            isCancelable = isCancelable,
            dismissAction = dismissAction
    )
    dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
    if(!dialog.isShowing) dialog.show()
    return dialog

}

fun Context.checkCameraFront(): Boolean {
    return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
}
