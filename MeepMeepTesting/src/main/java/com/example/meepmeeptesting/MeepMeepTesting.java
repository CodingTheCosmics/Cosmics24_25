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
                .setConstraints(30, 30, 5.82005, 4.6494, 10.51)
                .build();

        final double TIME = 0.5;


        //predefined poses/vectors
        Pose2d startPose = new Pose2d(30.75, 58.25, 0);

        Vector2d bucketVector = new Vector2d(-56, -55.25);

        Pose2d START_POSE = new Pose2d(30.75, 58.25, 0);

        Vector2d BUCKET_VECTOR = new Vector2d(57, 51);

        Pose2d BUCKET_POSE = new Pose2d(56, 51, Math.toRadians(45));

         Pose2d FIELD_POSE_1 = new Pose2d(53, 43, Math.toRadians(270));
         Pose2d FIELD_POSE_2 = new Pose2d(42, 23, Math.toRadians(0));
         Pose2d FIELD_POSE_3 = new Pose2d(51, 22, Math.toRadians(0));

         Pose2d PARK_POSE = FIELD_POSE_3;

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(57, 51, Math.toRadians(45))
                //cycle to buckets

                //lift and drive to bucket





                //sample dropped into bucket (1)


                //lift down and drive to field


                // .setVelConstraint(slowConstraint)
               // .splineTo(new Vector2d())
              //  .waitSeconds(TIME/2)




                //sample picked up from field (2)


                //drive to bucket


                // .setVelConstraint(slowConstraint)
              /*  .lineToLinearHeading(BUCKET_POSE)
                .waitSeconds(TIME)




                //sample dropped into bucket (2)


                //lift down and drive to field


                //  .setVelConstraint(slowConstraint)
                .lineToLinearHeading(FIELD_POSE_2)
                .waitSeconds(TIME/2)



                //picked up new sample (3)


                //drive to bucket


                // .setVelConstraint(slowConstraint)
                .lineToLinearHeading(BUCKET_POSE)
                                .waitSeconds(TIME))


                //sample dropped into bucket (3)




                // .setVelConstraint(slowConstraint)
                .lineToLinearHeading(FIELD_POSE_3)
                .waitSeconds(TIME/2)



                //picked up new sample (4)


                //drive to bucket
                //.setVelConstraint(slowConstraint)
                .lineToLinearHeading(BUCKET_POSE)
                .waitSeconds(TIME)

                //lift down


                // .setVelConstraint(slowConstraint)
                .lineToLinearHeading(PARK_POSE)

                //dropped sample into bucket (4) */


               // .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start()
    }
}