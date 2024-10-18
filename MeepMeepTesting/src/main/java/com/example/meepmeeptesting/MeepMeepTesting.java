package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseMap;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 30, 4.639, Math.toRadians(30), 11.35)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(16, 65, Math.toRadians(0)))
                .strafeTo(new Vector2d(46, 65))
                //insert marker to drop preloaded sample into bucket
                .splineTo(new Vector2d(55, 40), Math.toRadians(-90))
                //insert marker to pick up new sample
                .lineToY(50)
                .turnTo(Math.toRadians(-270))
                //insert marker to drop sample into bucket


                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}