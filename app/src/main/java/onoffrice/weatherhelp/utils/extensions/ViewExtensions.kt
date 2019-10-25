package onoffrice.weatherhelp.utils.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import onoffrice.weatherhelp.R
import java.io.File
import java.util.concurrent.TimeUnit



fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

//Animations

/** Animates List item views
 * firstPositionDelay is necessary cause  first position also needs a delay, but it's 0 **/
fun View.fadeUpItemListAnimation(position: Int, animationDelay: Long) {
    val firstPositionDelay = 2
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_up_item)
    animation.startOffset = (position + firstPositionDelay) * animationDelay

    this.animation = animation
}

/** Animates List item views
 * firstPositionDelay is necessary cause first position also needs a delay, but it's 0 **/
fun View.fadeInItemListAnimation(position: Int, animationDelay: Long) {
    val firstPositionDelay = 2
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_item)
    animation.startOffset = (position + firstPositionDelay) * animationDelay

    this.animation = animation
}

fun View.closeWithFadeOut(context: Context) {
    val aniFade = AnimationUtils.loadAnimation(context, R.anim.anim_fade_out)
    this.startAnimation(aniFade)
    this.setVisible(false)
}

fun View.openWithFadeIn(context: Context) {
    val aniFade = AnimationUtils.loadAnimation(context, R.anim.anim_fade_in)
    this.setVisible(true)
    this.startAnimation(aniFade)
}

fun View.fadeInAnimation(context: Context) {
    val animation = AnimationUtils.loadAnimation(context, R.anim.logo_transition)
    animation.repeatCount    = 1
    animation.duration       = 2000
    animation.fillAfter      = true
    animation.repeatMode     = Animation.REVERSE

    this.startAnimation(animation)
}