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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-7, 65, Math.toRadians(-90)))
                //go to bar
                .splineTo(new Vector2d(-7, 35), Math.toRadians(-90))
                .waitSeconds(1)
                //insert marker to hang specimen (1)

                //go to samples on field
                .strafeTo(new Vector2d(-45, 40))
                .waitSeconds(1)
                //insert marker to pick up new sample (2)

                //go to human player zone
                .strafeTo(new Vector2d(-35, 65))
                .turnTo(Math.toRadians(45))
                .waitSeconds(1)
                //insert marker to drop sample into human player zone (2)

                //go back to sample on field
                .strafeTo(new Vector2d(-45, 25))
                .waitSeconds(1)
                //insert marker to get new sample (3)

                //go back to human player station
                .strafeTo(new Vector2d(-35, 65))
                .waitSeconds(1)
                //insert marker to drop new sample (3)

                //go to sample on field
                .strafeTo(new Vector2d(-55, 25))
                .waitSeconds(1)
                //insert marker to pick up sample (4)

                //go to human player station
                .strafeTo(new Vector2d(-35, 65))
                .waitSeconds(1)
                //insert marker to drop off sample (4)

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}