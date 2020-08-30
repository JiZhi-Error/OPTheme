package com.jizhi.optheme

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jizhi.optheme.sdk.aod.entity.AodImageConfig
import com.jizhi.optheme.sdk.aod.entity.AodImageFile
import com.jizhi.optheme.sdk.aod.entity.AodImageFileListConfig
import com.jizhi.optheme.sdk.xp.tstorage.hok.XposedDataStorageClient
import com.jizhi.optheme.sdk.xp.tstorage.hok.util.XposedDataStorage
import dev.utils.app.AppCommonUtils
import dev.utils.app.UriUtils
import dev.utils.app.VibrationUtils
import dev.utils.app.image.ImageUtils
import dev.utils.app.permission.PermissionUtils
import dev.utils.common.FileIOUtils
import dev.utils.common.FileUtils
import kotlinx.android.synthetic.main.activity_aod_image.*
import pl.droidsonroids.gif.GifDrawable
import top.fols.box.io.XStream
import java.io.File
import java.io.FileInputStream
import kotlin.concurrent.thread


class AodImageActivity : AppCompatActivity() {

    val TAG = this::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aod_image)
        PermissionUtils.permission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).callBack(object : PermissionUtils.PermissionCallBack {
            /**
             * 授权通过权限回调
             */
            override fun onGranted() {
                Toast.makeText(this@AodImageActivity, "权限成功获取", Toast.LENGTH_SHORT).show()
            }

            /**
             * 授权未通过权限回调
             * @param grantedList  申请通过的权限
             * @param deniedList   申请未通过的权限
             * @param notFoundList 查询不到的权限 ( 包含未注册 )
             */
            override fun onDenied(
                grantedList: MutableList<String>?,
                deniedList: MutableList<String>?,
                notFoundList: MutableList<String>?
            ) {
                Toast.makeText(this@AodImageActivity, "失败", Toast.LENGTH_SHORT).show()
            }

        }).request(this)

        val client = XposedDataStorageClient()
        val methodsInterface = client.methodsInterface
        var aodImageFileListConfig = methodsInterface.aodImageFileListConfig
        if (aodImageFileListConfig==null){
            aodImageFileListConfig = AodImageFileListConfig()
        }
        val aodImageFileList = aodImageFileListConfig.aodImageFileList
        aodImageFileList.forEach {
            if (it.enabled) {
                val uriToFileQ = methodsInterface.getBitmap(it.filePath).get(methodsInterface)
                Log.i(TAG, "onActivityResult: $uriToFileQ")
                val gif = ImageUtils.isGif(uriToFileQ)
                if (gif) {
                    Log.i(TAG, "onActivityResult: gif")
                    val gifFromBytes = GifDrawable(uriToFileQ)
                    image.setImageDrawable(gifFromBytes)
                } else {
                    image!!.setImageDrawable(
                        ImageUtils.byteToDrawable(uriToFileQ)
                    )
                }
            }
        }
        val aodImageConfig = AodImageConfig()
        but1.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.GONE
            et_atyMoreSettings_marginRight.visibility = View.GONE
            et_atyMoreSettings_marginTop.visibility = View.VISIBLE
            et_atyMoreSettings_marginBottom.visibility = View.VISIBLE
            et_atyMoreSettings_marginLeft.text = null
            et_atyMoreSettings_marginRight.text = null
            aodImageConfig.aodImageGravity = Gravity.CENTER_HORIZONTAL
            Toast.makeText(this, "水平居中", Toast.LENGTH_SHORT).show()
            VibrationUtils.vibrate(2)
        }
        but2.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.VISIBLE
            et_atyMoreSettings_marginRight.visibility = View.VISIBLE
            et_atyMoreSettings_marginTop.visibility = View.GONE
            et_atyMoreSettings_marginBottom.visibility = View.GONE
            et_atyMoreSettings_marginTop.text = null
            et_atyMoreSettings_marginBottom.text = null
            aodImageConfig.aodImageGravity = Gravity.CENTER_VERTICAL
            Toast.makeText(this, "垂直居中", Toast.LENGTH_SHORT).show()
            VibrationUtils.vibrate(2)
        }
        but3.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.GONE
            et_atyMoreSettings_marginRight.visibility = View.GONE
            et_atyMoreSettings_marginTop.visibility = View.GONE
            et_atyMoreSettings_marginBottom.visibility = View.GONE
            et_atyMoreSettings_marginTop.text = null
            et_atyMoreSettings_marginBottom.text = null
            et_atyMoreSettings_marginLeft.text = null
            et_atyMoreSettings_marginRight.text = null
            aodImageConfig.aodImageGravity = Gravity.CENTER
            Toast.makeText(this, "居中", Toast.LENGTH_SHORT).show()
            VibrationUtils.vibrate(2)
        }
        but4.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.VISIBLE
            et_atyMoreSettings_marginRight.visibility = View.VISIBLE
            et_atyMoreSettings_marginTop.visibility = View.VISIBLE
            et_atyMoreSettings_marginBottom.visibility = View.VISIBLE
            et_atyMoreSettings_marginTop.text = null
            et_atyMoreSettings_marginBottom.text = null
            et_atyMoreSettings_marginLeft.text = null
            et_atyMoreSettings_marginRight.text = null
            aodImageConfig.aodImageGravity = Gravity.NO_GRAVITY
            Toast.makeText(this, "无", Toast.LENGTH_SHORT).show()
            VibrationUtils.vibrate(2)
        }

        but5.setOnClickListener {
            VibrationUtils.vibrate(2)
            aodImageConfig.aodImageWidth = et_atyMoreSettings_width.text.toString().toInt()
            aodImageConfig.aodImageHeight = et_atyMoreSettings_height.text.toString().toInt()
            val marginLeft = et_atyMoreSettings_marginLeft.text.toString()
            val marginTop = et_atyMoreSettings_marginTop.text.toString()
            val marginRight = et_atyMoreSettings_marginRight.text.toString()
            val marginBottom = et_atyMoreSettings_marginBottom.text.toString()
            Log.i(TAG, "onCreate: marginLeft ：$marginLeft")
            Log.i(TAG, "onCreate: marginTop ：$marginTop")
            Log.i(TAG, "onCreate: marginRight ：$marginRight")
            Log.i(TAG, "onCreate: marginBottom ：$marginBottom")
            if (marginLeft.isEmpty()) {
                aodImageConfig.aodImageMarginsLeft = 0
            } else {
                aodImageConfig.aodImageMarginsLeft = marginLeft.toInt()
            }
            if (marginTop.isEmpty()) {
                aodImageConfig.aodImageMarginsTop = 0
            } else {
                aodImageConfig.aodImageMarginsTop = marginTop.toInt()
            }
            if (marginRight.isEmpty()) {
                aodImageConfig.aodImageMarginsRight = 0
            } else {
                aodImageConfig.aodImageMarginsRight = marginRight.toInt()
            }
            if (marginBottom.isEmpty()) {
                aodImageConfig.aodImageMarginsBottom = 0
            } else {
                aodImageConfig.aodImageMarginsBottom = marginBottom.toInt()
            }
            if (filePath.isEmpty()) {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            aodImageConfig.aodViewDrawableFile = filePath
//            aodImageConfig.aodViewDrawable = R.drawable.diewu
            Log.i(TAG, "onCreate: $aodImageConfig")
            LiveEventBus.get(
                "com.jizhi.optheme.sdk\$AodImageConfig",
                AodImageConfig::class.java
            ).broadcast(aodImageConfig, true, false)
            val client = try {
                XposedDataStorageClient()
            } catch (e: Exception) {
                return@setOnClickListener
            } catch (e: ExceptionInInitializerError) {
                return@setOnClickListener
            }
            if (!client.isServiceAvailable) {
                return@setOnClickListener
            }
            if (!client.isServiceVersionEquals) {
                return@setOnClickListener
            }
            val methodsInterface = client.methodsInterface
            methodsInterface.aodImageConfig = aodImageConfig
            Toast.makeText(this, "确定", Toast.LENGTH_SHORT).show()
        }

        but11.setOnClickListener {
            VibrationUtils.vibrate(2)
            et_atyMoreSettings_width.visibility = View.GONE
            et_atyMoreSettings_width.setText("" + ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        but12.setOnClickListener {
            VibrationUtils.vibrate(2)
            et_atyMoreSettings_width.visibility = View.GONE
            et_atyMoreSettings_width.setText("" + ViewGroup.LayoutParams.MATCH_PARENT)
        }
        but13.setOnClickListener {
            VibrationUtils.vibrate(2)
            et_atyMoreSettings_width.visibility = View.VISIBLE
            et_atyMoreSettings_width.setText("")
        }
        but21.setOnClickListener {
            VibrationUtils.vibrate(2)
            et_atyMoreSettings_height.visibility = View.GONE
            et_atyMoreSettings_height.setText("" + ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        but22.setOnClickListener {
            VibrationUtils.vibrate(2)
            et_atyMoreSettings_height.visibility = View.GONE
            et_atyMoreSettings_height.setText("" + ViewGroup.LayoutParams.MATCH_PARENT)
        }
        but23.setOnClickListener {
            VibrationUtils.vibrate(2)
            et_atyMoreSettings_height.visibility = View.VISIBLE
            et_atyMoreSettings_height.setText("")
        }


        select.setOnClickListener {
            val granted = PermissionUtils.isGranted(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (!granted) {
                Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            val permissions = PermissionUtils.getPermissions()
//            Log.i(TAG, "onCreate: $permissions")
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 1)
        }

    }

    var filePath: String = ""
//    var image: ImageView? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data!!.data
            val uriToFileQ = UriUtils.getFilePathByUri(uri!!, AppCommonUtils.isQ())
            Log.i(TAG, "onActivityResult: $uriToFileQ")
            val gif = ImageUtils.isGif(FileIOUtils.readFileToBytesByChannel(uriToFileQ))
            if (gif) {
                Log.i(TAG, "onActivityResult: gif")
                val gifFromBytes = GifDrawable(uriToFileQ)
                image.setImageDrawable(gifFromBytes)
            } else {
                image!!.setImageDrawable(
                    ImageUtils.bitmapToDrawable(
                        ImageUtils.decodeFile(
                            uriToFileQ
                        )
                    )
                )
            }
            thread(true) {
                val fileName = FileUtils.getName(uriToFileQ)
                val client = XposedDataStorageClient()
                if (!client.methodsInterface.checkAodImageFolder()) {
                    runOnUiThread {
                        Toast.makeText(this, "文件夹权限问题，请检查", Toast.LENGTH_SHORT).show()
                    }
                    return@thread
                }
                try {
                    val storage = XposedDataStorage(client)
                    val fileInputStream = FileInputStream(uriToFileQ)
                    val absolutePathFileRandomOutputStream =
                        storage.getAbsolutePathFileRandomOutputStream(File("${XposedDataStorage.ROOT_DIR}/aodImage/$fileName"))
                    XStream.copy(fileInputStream, absolutePathFileRandomOutputStream)
                } catch (e: Exception) {
                    Log.e(TAG, "onActivityResult: ", e)
                }
                filePath = "${XposedDataStorage.ROOT_DIR}/aodImage/$fileName"
                LiveEventBus.get(
                    "com.jizhi.optheme.sdk\$AodImageFileAdd",
                    AodImageFile::class.java
                ).broadcast(
                    AodImageFile(
                        gif = gif,
                        fileName = fileName,
                        filePath = "${XposedDataStorage.ROOT_DIR}/aodImage/$fileName"
                    ), true, false
                )

            }
        }
    }
}