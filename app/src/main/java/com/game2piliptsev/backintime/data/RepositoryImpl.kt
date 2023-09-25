package com.game2piliptsev.backintime.data

import com.game2piliptsev.backintime.R
import com.game2piliptsev.backintime.domain.entities.LevelEntity
import com.game2piliptsev.backintime.domain.repository.Repository

object RepositoryImpl : Repository {

    private var levelList: List<LevelEntity> = arrayListOf(
        LevelEntity(
            R.drawable.audio_cassettes_8_track_royalty,
            1,
            R.array.question1_array
        ),
        LevelEntity(
            R.drawable.coffee_maker_in_retro_style,
            2,
            R.array.question2_array
        ),
        LevelEntity(
            R.drawable.delicious_swiss_cheese_fondue,
            4,
            R.array.question3_array
        ),
        LevelEntity(
            R.drawable.der_legendaere_ball_chair,
            2,
            R.array.question4_array
        ),
        LevelEntity(
            R.drawable.jake_the_janitor_bedside_lava_lamp_at_motel,
            2,
            R.array.question5_array
        ),
        LevelEntity(
            R.drawable.clock,
            2,
            R.array.question6_array
        ),
        LevelEntity(
            R.drawable.minolta_xl_440,
            1,
            R.array.question7_array
        ),
        LevelEntity(
            R.drawable.mirror_in_a_hall,
            4,
            R.array.question8_array
        ),
        LevelEntity(
            R.drawable.music_with_vinyls_royalty,
            3,
            R.array.question9_array
        ),
        LevelEntity(
            R.drawable.old_fashioned_tv,
            1,
            R.array.question10_array
        ),
        LevelEntity(
            R.drawable.stereo_equipment_in_a_living_room_royalty,
            1,
            R.array.question11_array
        ),
        LevelEntity(
            R.drawable.typing_news,
            3,
            R.array.question12_array
        ),
        LevelEntity(
            R.drawable.vintage_70s_clock_radio_with_floral_wallpaper,
            2,
            R.array.question13_array
        ),
        LevelEntity(
            R.drawable.vintage_old_radio_on_sixties_seventies_wallpaper,
            4,
            R.array.question14_array
        )
    ).shuffled()

    private var numberOfCorrectAnswers = 0

    override fun savePlayerAnswer(number: Int, answer: Int) {
        if (levelList[number].correctAnswer == answer)
            numberOfCorrectAnswers++
    }

    override fun getLevelByNumber(number: Int): LevelEntity = levelList[number]

    override fun getCountOfCorrectAnswers(): Int {
        val returnResult = numberOfCorrectAnswers
        numberOfCorrectAnswers = 0
        levelList = levelList.shuffled()
        return returnResult
    }

    override fun getCurrentScore(): Int = numberOfCorrectAnswers

    override fun subtractTwoPoints() {
        numberOfCorrectAnswers -= 2
    }
}