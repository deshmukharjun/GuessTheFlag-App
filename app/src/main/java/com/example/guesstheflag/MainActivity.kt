package com.example.guesstheflag

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val countryFlags = mapOf(
        "ad" to Pair("Andorra", R.drawable.ad),
        "ae" to Pair("United Arab Emirates", R.drawable.ae),
        "af" to Pair("Afghanistan", R.drawable.af),
        "ag" to Pair("Antigua and Barbuda", R.drawable.ag),
        "ai" to Pair("Anguilla", R.drawable.ai),
        "al" to Pair("Albania", R.drawable.al),
        "am" to Pair("Armenia", R.drawable.am),
        "ao" to Pair("Angola", R.drawable.ao),
        "aq" to Pair("Antarctica", R.drawable.aq),
        "ar" to Pair("Argentina", R.drawable.ar),
        "as" to Pair("American Samoa", R.drawable.`as`),
        "at" to Pair("Austria", R.drawable.at),
        "au" to Pair("Australia", R.drawable.au),
        "aw" to Pair("Aruba", R.drawable.aw),
        "ax" to Pair("Åland Islands", R.drawable.ax),
        "az" to Pair("Azerbaijan", R.drawable.az),
        "ba" to Pair("Bosnia and Herzegovina", R.drawable.ba),
        "bb" to Pair("Barbados", R.drawable.bb),
        "bd" to Pair("Bangladesh", R.drawable.bd),
        "be" to Pair("Belgium", R.drawable.be),
        "bf" to Pair("Burkina Faso", R.drawable.bf),
        "bg" to Pair("Bulgaria", R.drawable.bg),
        "bh" to Pair("Bahrain", R.drawable.bh),
        "bi" to Pair("Burundi", R.drawable.bi),
        "bj" to Pair("Benin", R.drawable.bj),
        "bl" to Pair("Saint Barthélemy", R.drawable.bl),
        "bm" to Pair("Bermuda", R.drawable.bm),
        "bn" to Pair("Brunei Darussalam", R.drawable.bn),
        "bo" to Pair("Bolivia, Plurinational State of", R.drawable.bo),
        "bq" to Pair("Caribbean Netherlands", R.drawable.bq),
        "br" to Pair("Brazil", R.drawable.br),
        "bs" to Pair("Bahamas", R.drawable.bs),
        "bt" to Pair("Bhutan", R.drawable.bt),
        "bv" to Pair("Bouvet Island", R.drawable.bv),
        "bw" to Pair("Botswana", R.drawable.bw),
        "by" to Pair("Belarus", R.drawable.by),
        "bz" to Pair("Belize", R.drawable.bz),
        "ca" to Pair("Canada", R.drawable.ca),
        "cc" to Pair("Cocos (Keeling) Islands", R.drawable.cc),
        "cd" to Pair("Congo, the Democratic Republic of the", R.drawable.cd),
        "cf" to Pair("Central African Republic", R.drawable.cf),
        "cg" to Pair("Republic of the Congo", R.drawable.cg),
        "ch" to Pair("Switzerland", R.drawable.ch),
        "ci" to Pair("Côte d'Ivoire", R.drawable.ci),
        "ck" to Pair("Cook Islands", R.drawable.ck),
        "cl" to Pair("Chile", R.drawable.cl),
        "cm" to Pair("Cameroon", R.drawable.cm),
        "cn" to Pair("China (People's Republic of China)", R.drawable.cn),
        "co" to Pair("Colombia", R.drawable.co),
        "cr" to Pair("Costa Rica", R.drawable.cr),
        "cu" to Pair("Cuba", R.drawable.cu),
        "cv" to Pair("Cape Verde", R.drawable.cv),
        "cw" to Pair("Curaçao", R.drawable.cw),
        "cx" to Pair("Christmas Island", R.drawable.cx),
        "cy" to Pair("Cyprus", R.drawable.cy),
        "cz" to Pair("Czech Republic", R.drawable.cz),
        "de" to Pair("Germany", R.drawable.de),
        "dj" to Pair("Djibouti", R.drawable.dj),
        "dk" to Pair("Denmark", R.drawable.dk),
        "dm" to Pair("Dominica", R.drawable.dm),
        "dr" to Pair("Dominican Republic", R.drawable.dr),
        "dz" to Pair("Algeria", R.drawable.dz),
        "ec" to Pair("Ecuador", R.drawable.ec),
        "ee" to Pair("Estonia", R.drawable.ee),
        "eg" to Pair("Egypt", R.drawable.eg),
        "eh" to Pair("Western Sahara", R.drawable.eh),
        "er" to Pair("Eritrea", R.drawable.er),
        "es" to Pair("Spain", R.drawable.es),
        "et" to Pair("Ethiopia", R.drawable.et),
        "eu" to Pair("Europe", R.drawable.eu),
        "fi" to Pair("Finland", R.drawable.fi),
        "fj" to Pair("Fiji", R.drawable.fj),
        "fk" to Pair("Falkland Islands (Malvinas)", R.drawable.fk),
        "fm" to Pair("Micronesia, Federated States of", R.drawable.fm),
        "fo" to Pair("Faroe Islands", R.drawable.fo),
        "fr" to Pair("France", R.drawable.fr),
        "ga" to Pair("Gabon", R.drawable.ga),
        "gb" to Pair("United Kingdom", R.drawable.gb),
        "gbeng" to Pair("England", R.drawable.gbeng),
        "gbnir" to Pair("Northern Ireland", R.drawable.gbnir),
        "gbsct" to Pair("Scotland", R.drawable.gbsct),
        "gbwls" to Pair("Wales", R.drawable.gbwls),
        "gd" to Pair("Grenada", R.drawable.gd),
        "ge" to Pair("Georgia", R.drawable.ge),
        "gf" to Pair("French Guiana", R.drawable.gf),
        "gg" to Pair("Guernsey", R.drawable.gg),
        "gh" to Pair("Ghana", R.drawable.gh),
        "gi" to Pair("Gibraltar", R.drawable.gi),
        "gl" to Pair("Greenland", R.drawable.gl),
        "gm" to Pair("Gambia", R.drawable.gm),
        "gn" to Pair("Guinea", R.drawable.gn),
        "gp" to Pair("Guadeloupe", R.drawable.gp),
        "gq" to Pair("Equatorial Guinea", R.drawable.gq),
        "gr" to Pair("Greece", R.drawable.gr),
        "gs" to Pair("South Georgia and the South Sandwich Islands", R.drawable.gs),
        "gt" to Pair("Guatemala", R.drawable.gt),
        "gu" to Pair("Guam", R.drawable.gu),
        "gw" to Pair("Guinea-Bissau", R.drawable.gw),
        "gy" to Pair("Guyana", R.drawable.gy),
        "hk" to Pair("Hong Kong", R.drawable.hk),
        "hm" to Pair("Heard Island and McDonald Islands", R.drawable.hm),
        "hn" to Pair("Honduras", R.drawable.hn),
        "hr" to Pair("Croatia", R.drawable.hr),
        "ht" to Pair("Haiti", R.drawable.ht),
        "hu" to Pair("Hungary", R.drawable.hu),
        "id" to Pair("Indonesia", R.drawable.id),
        "ie" to Pair("Ireland", R.drawable.ie),
        "il" to Pair("Israel", R.drawable.il),
        "im" to Pair("Isle of Man", R.drawable.im),
        "in" to Pair("India", R.drawable.`in`),
        "io" to Pair("British Indian Ocean Territory", R.drawable.io),
        "iq" to Pair("Iraq", R.drawable.iq),
        "ir" to Pair("Iran, Islamic Republic of", R.drawable.ir),
        "is" to Pair("Iceland", R.drawable.`is`),
        "it" to Pair("Italy", R.drawable.it),
        "je" to Pair("Jersey", R.drawable.je),
        "jm" to Pair("Jamaica", R.drawable.jm),
        "jo" to Pair("Jordan", R.drawable.jo),
        "jp" to Pair("Japan", R.drawable.jp),
        "ke" to Pair("Kenya", R.drawable.ke),
        "kg" to Pair("Kyrgyzstan", R.drawable.kg),
        "kh" to Pair("Cambodia", R.drawable.kh),
        "ki" to Pair("Kiribati", R.drawable.ki),
        "km" to Pair("Comoros", R.drawable.km),
        "kn" to Pair("Saint Kitts and Nevis", R.drawable.kn),
        "kp" to Pair("Korea, Democratic People's Republic of", R.drawable.kp),
        "kr" to Pair("Korea, Republic of", R.drawable.kr),
        "kw" to Pair("Kuwait", R.drawable.kw),
        "ky" to Pair("Cayman Islands", R.drawable.ky),
        "kz" to Pair("Kazakhstan", R.drawable.kz),
        "la" to Pair("Laos (Lao People's Democratic Republic)", R.drawable.la),
        "lb" to Pair("Lebanon", R.drawable.lb),
        "lc" to Pair("Saint Lucia", R.drawable.lc),
        "li" to Pair("Liechtenstein", R.drawable.li),
        "lk" to Pair("Sri Lanka", R.drawable.lk),
        "lr" to Pair("Liberia", R.drawable.lr),
        "ls" to Pair("Lesotho", R.drawable.ls),
        "lt" to Pair("Lithuania", R.drawable.lt),
        "lu" to Pair("Luxembourg", R.drawable.lu),
        "lv" to Pair("Latvia", R.drawable.lv),
        "ly" to Pair("Libya", R.drawable.ly),
        "ma" to Pair("Morocco", R.drawable.ma),
        "mc" to Pair("Monaco", R.drawable.mc),
        "md" to Pair("Moldova, Republic of", R.drawable.md),
        "me" to Pair("Montenegro", R.drawable.me),
        "mf" to Pair("Saint Martin", R.drawable.mf),
        "mg" to Pair("Madagascar", R.drawable.mg),
        "mh" to Pair("Marshall Islands", R.drawable.mh),
        "mk" to Pair("North Macedonia", R.drawable.mk),
        "ml" to Pair("Mali", R.drawable.ml),
        "mm" to Pair("Myanmar", R.drawable.mm),
        "mn" to Pair("Mongolia", R.drawable.mn),
        "mo" to Pair("Macao", R.drawable.mo),
        "mp" to Pair("Northern Mariana Islands", R.drawable.mp),
        "mq" to Pair("Martinique", R.drawable.mq),
        "mr" to Pair("Mauritania", R.drawable.mr),
        "ms" to Pair("Montserrat", R.drawable.ms),
        "mt" to Pair("Malta", R.drawable.mt),
        "mu" to Pair("Mauritius", R.drawable.mu),
        "mv" to Pair("Maldives", R.drawable.mv),
        "mw" to Pair("Malawi", R.drawable.mw),
        "mx" to Pair("Mexico", R.drawable.mx),
        "my" to Pair("Malaysia", R.drawable.my),
        "mz" to Pair("Mozambique", R.drawable.mz),
        "na" to Pair("Namibia", R.drawable.na),
        "nc" to Pair("New Caledonia", R.drawable.nc),
        "ne" to Pair("Niger", R.drawable.ne),
        "nf" to Pair("Norfolk Island", R.drawable.nf),
        "ng" to Pair("Nigeria", R.drawable.ng),
        "ni" to Pair("Nicaragua", R.drawable.ni),
        "nl" to Pair("Netherlands", R.drawable.nl),
        "no" to Pair("Norway", R.drawable.no),
        "np" to Pair("Nepal", R.drawable.np),
        "nr" to Pair("Nauru", R.drawable.nr),
        "nu" to Pair("Niue", R.drawable.nu),
        "nz" to Pair("New Zealand", R.drawable.nz),
        "om" to Pair("Oman", R.drawable.om),
        "pa" to Pair("Panama", R.drawable.pa),
        "pe" to Pair("Peru", R.drawable.pe),
        "pf" to Pair("French Polynesia", R.drawable.pf),
        "pg" to Pair("Papua New Guinea", R.drawable.pg),
        "ph" to Pair("Philippines", R.drawable.ph),
        "pk" to Pair("Pakistan", R.drawable.pk),
        "pl" to Pair("Poland", R.drawable.pl),
        "pm" to Pair("Saint Pierre and Miquelon", R.drawable.pm),
        "pn" to Pair("Pitcairn", R.drawable.pn),
        "pr" to Pair("Puerto Rico", R.drawable.pr),
        "ps" to Pair("Palestine", R.drawable.ps),
        "pt" to Pair("Portugal", R.drawable.pt),
        "pw" to Pair("Palau", R.drawable.pw),
        "py" to Pair("Paraguay", R.drawable.py),
        "qa" to Pair("Qatar", R.drawable.qa),
        "re" to Pair("Réunion", R.drawable.re),
        "ro" to Pair("Romania", R.drawable.ro),
        "rs" to Pair("Serbia", R.drawable.rs),
        "ru" to Pair("Russian Federation", R.drawable.ru),
        "rw" to Pair("Rwanda", R.drawable.rw),
        "sa" to Pair("Saudi Arabia", R.drawable.sa),
        "sb" to Pair("Solomon Islands", R.drawable.sb),
        "sc" to Pair("Seychelles", R.drawable.sc),
        "sd" to Pair("Sudan", R.drawable.sd),
        "se" to Pair("Sweden", R.drawable.se),
        "sg" to Pair("Singapore", R.drawable.sg),
        "sh" to Pair("Saint Helena, Ascension and Tristan da Cunha", R.drawable.sh),
        "si" to Pair("Slovenia", R.drawable.si),
        "sj" to Pair("Svalbard and Jan Mayen Islands", R.drawable.sj),
        "sk" to Pair("Slovakia", R.drawable.sk),
        "sl" to Pair("Sierra Leone", R.drawable.sl),
        "sm" to Pair("San Marino", R.drawable.sm),
        "sn" to Pair("Senegal", R.drawable.sn),
        "so" to Pair("Somalia", R.drawable.so),
        "sr" to Pair("Suriname", R.drawable.sr),
        "ss" to Pair("South Sudan", R.drawable.ss),
        "st" to Pair("Sao Tome and Principe", R.drawable.st),
        "sv" to Pair("El Salvador", R.drawable.sv),
        "sx" to Pair("Sint Maarten (Dutch part)", R.drawable.sx),
        "sy" to Pair("Syrian Arab Republic", R.drawable.sy),
        "sz" to Pair("Swaziland", R.drawable.sz),
        "tc" to Pair("Turks and Caicos Islands", R.drawable.tc),
        "td" to Pair("Chad", R.drawable.td),
        "tf" to Pair("French Southern Territories", R.drawable.tf),
        "tg" to Pair("Togo", R.drawable.tg),
        "th" to Pair("Thailand", R.drawable.th),
        "tj" to Pair("Tajikistan", R.drawable.tj),
        "tk" to Pair("Tokelau", R.drawable.tk),
        "tl" to Pair("Timor-Leste", R.drawable.tl),
        "tm" to Pair("Turkmenistan", R.drawable.tm),
        "tn" to Pair("Tunisia", R.drawable.tn),
        "to" to Pair("Tonga", R.drawable.to),
        "tr" to Pair("Turkey", R.drawable.tr),
        "tt" to Pair("Trinidad and Tobago", R.drawable.tt),
        "tv" to Pair("Tuvalu", R.drawable.tv),
        "tw" to Pair("Taiwan (Republic of China)", R.drawable.tw),
        "tz" to Pair("Tanzania, United Republic of", R.drawable.tz),
        "ua" to Pair("Ukraine", R.drawable.ua),
        "ug" to Pair("Uganda", R.drawable.ug),
        "um" to Pair("US Minor Outlying Islands", R.drawable.um),
        "us" to Pair("United States", R.drawable.us),
        "uy" to Pair("Uruguay", R.drawable.uy),
        "uz" to Pair("Uzbekistan", R.drawable.uz),
        "va" to Pair("Holy See (Vatican City State)", R.drawable.va),
        "vc" to Pair("Saint Vincent and the Grenadines", R.drawable.vc),
        "ve" to Pair("Venezuela, Bolivarian Republic of", R.drawable.ve),
        "vg" to Pair("Virgin Islands, British", R.drawable.vg),
        "vi" to Pair("Virgin Islands, U.S.", R.drawable.vi),
        "vn" to Pair("Vietnam", R.drawable.vn),
        "vu" to Pair("Vanuatu", R.drawable.vu),
        "wf" to Pair("Wallis and Futuna Islands", R.drawable.wf),
        "ws" to Pair("Samoa", R.drawable.ws),
        "xk" to Pair("Kosovo", R.drawable.xk),
        "ye" to Pair("Yemen", R.drawable.ye),
        "yt" to Pair("Mayotte", R.drawable.yt),
        "za" to Pair("South Africa", R.drawable.za),
        "zm" to Pair("Zambia", R.drawable.zm),
        "zw" to Pair("Zimbabwe", R.drawable.zw)
    )

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayerCorrect: MediaPlayer
    private lateinit var mediaPlayerIncorrect: MediaPlayer

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView1)
        val nextButton = findViewById<ImageButton>(R.id.imageButton1)
        val hintButton = findViewById<ImageButton>(R.id.imageButton2)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val textViewScore = findViewById<TextView>(R.id.score)
        val textViewHighScore = findViewById<TextView>(R.id.highscore)

        var correctAnswer: String? = null
        var score = 0
        var highScore = 0

        textViewScore.text = "Score: $score"
        textViewHighScore.text = "High Score: $highScore"

        mediaPlayer = MediaPlayer.create(this, R.raw.click)
        mediaPlayerCorrect = MediaPlayer.create(this, R.raw.correct_ans)
        mediaPlayerIncorrect = MediaPlayer.create(this, R.raw.wrong_ans)

        fun resetButtonColors() {
            val buttons = listOf(button1, button2, button3, button4)
            buttons.forEach { it.setBackgroundResource(R.drawable.option_buttons_bg) }
        }

        fun setRandomFlagAndOptions() {
            resetButtonColors()
            val randomEntry = countryFlags.entries.random()
            val correctCountryCode = randomEntry.key
            val correctCountryName = randomEntry.value.first
            val correctFlagDrawable = randomEntry.value.second
            correctAnswer = correctCountryName

            imageView.setImageResource(correctFlagDrawable)

            val incorrectOptions = countryFlags.entries
                .filter { it.key != correctCountryCode }
                .shuffled()
                .take(3)
                .map { it.value.first }

            val allOptions = (incorrectOptions + correctCountryName).shuffled()

            button1.text = allOptions[0]
            button2.text = allOptions[1]
            button3.text = allOptions[2]
            button4.text = allOptions[3]
        }

        val buttonstoenable = listOf(button1, button2, button3, button4, hintButton, nextButton)

        fun disableAllButtons() {
            buttonstoenable.forEach { it.isEnabled = false }
        }

        fun enableAllButtons() {
            buttonstoenable.forEach { it.isEnabled = true }
        }

        fun checkAnswer(selectedButton: Button, selectedOption: String) {
            val buttons = listOf(button1, button2, button3, button4)
            disableAllButtons()
            if (selectedOption == correctAnswer) {
                mediaPlayerCorrect.start()
                selectedButton.setBackgroundResource(R.drawable.correct_option)
                score++
                if (score > highScore) {
                    highScore = score
                }
                textViewScore.text = "Score: $score"
                textViewHighScore.text = "High Score: $highScore"
            } else {
                mediaPlayerIncorrect.start()
                selectedButton.setBackgroundResource(R.drawable.incorrect_option)
                score = 0
                textViewScore.text = "Score: $score"

                buttons.forEach {
                    if (it.text == correctAnswer) {
                        it.setBackgroundResource(R.drawable.correct_option)
                    }
                }
            }

            Handler(Looper.getMainLooper()).postDelayed({
                setRandomFlagAndOptions()
                enableAllButtons()
            }, 2000)
        }

        val buttons = listOf(button1, button2, button3, button4)
        buttons.forEach { button ->
            button.setOnClickListener {
                checkAnswer(button, button.text.toString())
            }
        }

        setRandomFlagAndOptions()

        nextButton.setOnClickListener {
            mediaPlayer.start()
            setRandomFlagAndOptions()
            score = 0
            textViewScore.text = "Score: $score"
        }

        hintButton.setOnClickListener {
            mediaPlayer.start()
            val incorrectButtons = buttons.filter { it.text != correctAnswer && it.text.isNotEmpty() }
            if (incorrectButtons.isNotEmpty()) {
                val buttonToBlank = incorrectButtons.random()
                buttonToBlank.text = ""
            }
        }
    }
}
