package com.theresa.boutique.retrofit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("WebAppLog.asmx/FnGetUserLogIn")
    Call<JsonElement> login(
            @Field("PrmUserName") String name,
            @Field("PrmPassword") String password
    );

    @FormUrlEncoded
    @POST("WebAppCustomer.asmx/FnSaveCustomer")
    Call<JsonElement> saveCustomer(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmName") String Name,
            @Field("PrmCode") String Code,
            @Field("PrmDob") String DoB,
            @Field("PrmMobNo") String MobNo,
            @Field("PrmHouseName") String HouseName,
            @Field("PrmFamilyName") String FamilyName,
            @Field("PrmAddress") String Address,
            @Field("PrmPlace") String Place,
            @Field("PrmPhNo") String PhNo,
            @Field("PrmEmail") String Email,
            @Field("PrmWhatsAppNo") String Whatsapp,
            @Field("PrmFaceBook") String Facebook,
            @Field("PrmRemarks") String Remarks
    );

    @FormUrlEncoded
    @POST("WebAppCustomer.asmx/FnUpdateCustomer")
    Call<JsonElement> updateCustomer(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmId") String Id,
            @Field("PrmName") String Name,
            @Field("PrmCode") String Code,
            @Field("PrmDob") String DoB,
            @Field("PrmMobNo") String MobNo,
            @Field("PrmHouseName") String HouseName,
            @Field("PrmFamilyName") String FamilyName,
            @Field("PrmAddress") String Address,
            @Field("PrmPlace") String Place,
            @Field("PrmPhNo") String PhNo,
            @Field("PrmEmail") String Email,
            @Field("PrmWhatsAppNo") String Whatsapp,
            @Field("PrmFaceBook") String Facebook,
            @Field("PrmRemarks") String Remarks
    );

    @FormUrlEncoded
    @POST("WebAppCustomer.asmx/FnGetCustomerAutoCode")
    Call<JsonElement> getAutoCode(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId
    );

    @FormUrlEncoded
    @POST("WebAppCustomer.asmx/FnCustomerList")
    Call<JsonElement> searchCustomerList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmName") String Name,
            @Field("PrmCode") String Code,
            @Field("PrmMobNo") String MobNo,
            @Field("PrmHouseName") String HouseName,
            @Field("PrmFamilyName") String FamilyName,
            @Field("PrmAddress") String Address,
            @Field("PrmPlace") String Place,
            @Field("PrmPhNo") String PhNo,
            @Field("PrmEmail") String Email,
            @Field("PrmWhatsAppNo") String Whatsapp,
            @Field("PrmFaceBook") String Facebook,
            @Field("PrmRemarks") String Remarks
    );

    @GET("WebAppOrder.asmx/FnGetOrderNoAutoCode")
    Call<JsonElement> createOrderNo();

    @FormUrlEncoded
    @POST("WebAppOrder.asmx/FnSaveOrder")
    Call<JsonElement> saveOrder(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmCustomerId") String CustId,
            @Field("PrmIsAlteration") String IsAlter,
            @Field("PrmOrderNo") String OrderNo,
            @Field("PrmNoChuridar") String NoChuridar,
            @Field("PrmNoAnarkali") String NoAnarkali,
            @Field("PrmNoShwal") String NoShawl,
            @Field("PrmNoSaree") String NoSaree,
            @Field("PrmNoBlouse") String NoBlouse,
            @Field("PrmNoTopSkirt") String TopSkirt,
            @Field("PrmNoGown") String NoGown,
            @Field("PrmNoOverCoat") String NoOverCoat,
            @Field("PrmNoFrock") String NoFrock,
            @Field("PrmRemarks") String Remarks,
            @Field("PrmNoCs") String NoSimpleChuridar,
            @Field("PrmNoWb") String NoWeddingBlouse,
            @Field("PrmNoWn") String NoWeddingNet
    );

    @FormUrlEncoded
    @POST("WebAppOrder.asmx/FnUpdateOrder")
    Call<JsonElement> updateOrder(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmId") String OrderId,
            @Field("PrmCustomerId") String CustId,
            @Field("PrmIsAlteration") String IsAlter,
            @Field("PrmOrderNo") String OrderNo,
            @Field("PrmNoChuridar") String NoChuridar,
            @Field("PrmNoAnarkali") String NoAnarkali,
            @Field("PrmNoShwal") String NoShawl,
            @Field("PrmNoSaree") String NoSaree,
            @Field("PrmNoBlouse") String NoBlouse,
            @Field("PrmNoTopSkirt") String TopSkirt,
            @Field("PrmNoGown") String NoGown,
            @Field("PrmNoOverCoat") String NoOverCoat,
            @Field("PrmNoFrock") String NoFrock,
            @Field("PrmRemarks") String Remarks,
            @Field("PrmNoCs") String NoSimpleChuridar,
            @Field("PrmNoWb") String NoWeddingBlouse,
            @Field("PrmNoWn") String NoWeddingNet
    );

    @FormUrlEncoded
    @POST("WebAppOrder.asmx/FnGetOrderDressTypeList")
    Call<JsonElement> getOrderDressList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmOrderNo") String OrderNo
    );

    @FormUrlEncoded
    @POST("WebAppOrderMeasure.asmx/FnGetAlterationList")
    Call<JsonElement> getAlterationTypeList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId

    );

    @FormUrlEncoded
    @POST("WebAppOrdermeasure.asmx/FnSaveOrder")
    Call<JsonElement> saveOrderMeasure(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmCustomerId") String CustId,
            @Field("PrmIsAlteration") String isAlter,
            @Field("PrmOrderNo") String orderNo,
            @Field("PrmDressTypeNo") String DressTypeNo,
            @Field("PrmDressType") String DressType,
            @Field("PrmIsTop") String isTop,
            @Field("PrmTopValue") String topValue,
            @Field("PrmIsTopLining") String isTopLining,
            @Field("PrmTopLiningVal") String topLiningVal,
            @Field("PrmIsYoke") String isYoke,
            @Field("PrmYokeVal") String yokeVal,
            @Field("PrmIsYokeLining") String isYokeLining,
            @Field("PrmYokeLiningVal") String yokeLiningVal,
            @Field("PrmIsLace") String isLace,
            @Field("PrmLaceVal") String laceVal,
            @Field("PrmIsSleeve") String isSleeve,
            @Field("PrmSleeveVal") String sleeveVal,
            @Field("PrmIsSleeveLining") String isSleeveLining,
            @Field("PrmSleeveLiningVal") String sleeveLiningVal,
            @Field("PrmIsShawl") String isShawl,
            @Field("PrmShawlVal") String shawlVal,
            @Field("PrmIsBottom") String isBottom,
            @Field("PrmBottomVal") String bottomVal,
            @Field("PrmIsBottomLining") String isBottomLining,
            @Field("PrmBottomLiningVal") String bottomLiningVal,
            @Field("PrmPriority") String priority,
            @Field("PrmFuction") String function,
            @Field("PrmFabricTypeId") String fabricTypeId,
            @Field("PrmMeasurement") String measurement,
            @Field("PrmDesignerId") String designerId,
            @Field("PrmRemarks") String remarks,
            @Field("PrmIsZIP") String isZip,
            @Field("PrmIsTuck") String isTuck,
            @Field("PrmIsSleeveLiningM2") String isSleeveLiningM2,
            @Field("PrmIsSleevePiping") String isSleevePiping,
            @Field("PrmIsFlairFront") String isFlairFront,
            @Field("PrmIsFlairSide") String isFlairSide,
            @Field("PrmIsFlairSlit") String isFlairSlit,
            @Field("PrmSleeveLiningValM2") String sleeveLiningValM2,
            @Field("PrmYkkVal") String ykkVal,
            @Field("PrmYkkYesVal") String ykkYesVal,
            @Field("PrmYkkNoVal") String ykkNoVal,
            @Field("PrmIsSlitUmbrella") String isSlitUmbrella,
            @Field("PrmIsSlitScws") String isSlitScws,
            @Field("PrmIsSlitScwsFront") String isSlitScwsFront,
            @Field("PrmTuckType") String tuckType,
            @Field("PrmSleeveLiningType") String sleeveLiningType,
            @Field("PrmSleevePipingType") String sleevePipingType,
            @Field("PrmNeckType") String neckType,
            @Field("PrmNeckPiping") String neckPiping,
            @Field("PrmFlairType") String flairType,
            @Field("PrmFlairPiping") String flairPiping,
            @Field("PrmBottomType") String bottomType,
            @Field("PrmPocketType") String pocketType,
            @Field("PrmDesignerM2Id") String designerM2Id,
            @Field("PrmRemarksM2") String remarksM2,
            @Field("PrmDueDate") String dueDate,
            @Field("PrmModeType") String modeType,
            @Field("PrmIsWorkM3") String isWorkM3,
            @Field("PrmIsLiningM3") String isLiningM3,
            @Field("PrmTopMeasure") String topMeasure,
            @Field("PrmYokeFront") String yokeFront,
            @Field("PrmYokeBack") String yokeBack,
            @Field("PrmDesignerM3Id") String designerM3Id,
            @Field("PrmRemarksM3") String remarksM3,
            @Field("PrmOthers") String others,
            @Field("PrmSpeakerNet") String speakerNet,
            @Field("PrmSpeakerNetLining") String speakerNetLining,
            @Field("PrmPad") String pad,
            @Field("PrmPleat") String pleat,
            @Field("PrmTypeofFrock") String typeOfFrock,
            @Field("PrmTungies") String tungies,
            @Field("PrmTransparent") String transparent,
            @Field("PrmPanel") String panel,
            @Field("PrmWorkPiece") String workPiece,
            @Field("PrmPassels") String passels,
            @Field("PrmFall") String fall,
            @Field("PrmLock") String lock,
            @Field("PrmLockPiping") String lockPiping,
            @Field("PrmSkirt") String skirt,
            @Field("PrmSkirtLining") String skirtLining,
            @Field("PrmSkirtUmbrella") String skirtUmbrella,
            @Field("PrmHangings") String hangings,
            @Field("PrmOpen") String open,
            @Field("PrmPiping") String piping,
            @Field("PrmPipingLining") String pipingLining,
            @Field("PrmTassels") String tassels,
            @Field("PrmIsSpeakerNet") String isSpeakerNet,
            @Field("PrmIsSpeakerNetLining") String isSpeakerNetLining,
            @Field("PrmIsWorkPiece") String isWorkPiece,
            @Field("PrmIsSkirtLining") String isSkirtLining,
            @Field("PrmIsPiping") String isPiping,
            @Field("PrmIsPipingLining") String isPipingLining,
            @Field("PrmIsSkirtSpeakerNet") String isSkirtSpeakerner,
            @Field("PrmIsSkirtSpeakerNetLining") String isSkirtSpeakerNetLining,
            @Field("PrmSkirtOthers") String skirtOthers,
            @Field("PrmIsSkirt") String IsSkirt,
            @Field("PrmZipHidingVal") String ZipHidingVal,
            @Field("PrmZipNomalVal") String ZipNomalVal,
            @Field("PrmSkirtMeasure") String SkirtMeasure,
            @Field("PrmIsStraight") String IsStraight,
            @Field("PrmAlterationType") String alterType
    );

    @FormUrlEncoded
    @POST("WebAppOrdermeasure.asmx/FnUpdateOrder")
    Call<JsonElement> updateOrderMeasure(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmId") String Id,
            @Field("PrmCustomerId") String CustId,
            @Field("PrmIsAlteration") String isAlter,
            @Field("PrmOrderNo") String orderNo,
            @Field("PrmDressTypeNo") String DressTypeNo,
            @Field("PrmDressType") String DressType,
            @Field("PrmIsTop") String isTop,
            @Field("PrmTopValue") String topValue,
            @Field("PrmIsTopLining") String isTopLining,
            @Field("PrmTopLiningVal") String topLiningVal,
            @Field("PrmIsYoke") String isYoke,
            @Field("PrmYokeVal") String yokeVal,
            @Field("PrmIsYokeLining") String isYokeLining,
            @Field("PrmYokeLiningVal") String yokeLiningVal,
            @Field("PrmIsLace") String isLace,
            @Field("PrmLaceVal") String laceVal,
            @Field("PrmIsSleeve") String isSleeve,
            @Field("PrmSleeveVal") String sleeveVal,
            @Field("PrmIsSleeveLining") String isSleeveLining,
            @Field("PrmSleeveLiningVal") String sleeveLiningVal,
            @Field("PrmIsShawl") String isShawl,
            @Field("PrmShawlVal") String shawlVal,
            @Field("PrmIsBottom") String isBottom,
            @Field("PrmBottomVal") String bottomVal,
            @Field("PrmIsBottomLining") String isBottomLining,
            @Field("PrmBottomLiningVal") String bottomLiningVal,
            @Field("PrmPriority") String priority,
            @Field("PrmFuction") String function,
            @Field("PrmFabricTypeId") String fabricTypeId,
            @Field("PrmMeasurement") String measurement,
            @Field("PrmDesignerId") String designerId,
            @Field("PrmRemarks") String remarks,
            @Field("PrmIsZIP") String isZip,
            @Field("PrmIsTuck") String isTuck,
            @Field("PrmIsSleeveLiningM2") String isSleeveLiningM2,
            @Field("PrmIsSleevePiping") String isSleevePiping,
            @Field("PrmIsFlairFront") String isFlairFront,
            @Field("PrmIsFlairSide") String isFlairSide,
            @Field("PrmIsFlairSlit") String isFlairSlit,
            @Field("PrmSleeveLiningValM2") String sleeveLiningValM2,
            @Field("PrmYkkVal") String ykkVal,
            @Field("PrmYkkYesVal") String ykkYesVal,
            @Field("PrmYkkNoVal") String ykkNoVal,
            @Field("PrmIsSlitUmbrella") String isSlitUmbrella,
            @Field("PrmIsSlitScws") String isSlitScws,
            @Field("PrmIsSlitScwsFront") String isSlitScwsFront,
            @Field("PrmTuckType") String tuckType,
            @Field("PrmSleeveLiningType") String sleeveLiningType,
            @Field("PrmSleevePipingType") String sleevePipingType,
            @Field("PrmNeckType") String neckType,
            @Field("PrmNeckPiping") String neckPiping,
            @Field("PrmFlairType") String flairType,
            @Field("PrmFlairPiping") String flairPiping,
            @Field("PrmBottomType") String bottomType,
            @Field("PrmPocketType") String pocketType,
            @Field("PrmDesignerM2Id") String designerM2Id,
            @Field("PrmRemarksM2") String remarksM2,
            @Field("PrmDueDate") String dueDate,
            @Field("PrmModeType") String modeType,
            @Field("PrmIsWorkM3") String isWorkM3,
            @Field("PrmIsLiningM3") String isLiningM3,
            @Field("PrmTopMeasure") String topMeasure,
            @Field("PrmYokeFront") String yokeFront,
            @Field("PrmYokeBack") String yokeBack,
            @Field("PrmDesignerM3Id") String designerM3Id,
            @Field("PrmRemarksM3") String remarksM3,
            @Field("PrmOthers") String others,
            @Field("PrmSpeakerNet") String speakerNet,
            @Field("PrmSpeakerNetLining") String speakerNetLining,
            @Field("PrmPad") String pad,
            @Field("PrmPleat") String pleat,
            @Field("PrmTypeofFrock") String typeOfFrock,
            @Field("PrmTungies") String tungies,
            @Field("PrmTransparent") String transparent,
            @Field("PrmPanel") String panel,
            @Field("PrmWorkPiece") String workPiece,
            @Field("PrmPassels") String passels,
            @Field("PrmFall") String fall,
            @Field("PrmLock") String lock,
            @Field("PrmLockPiping") String lockPiping,
            @Field("PrmSkirt") String skirt,
            @Field("PrmSkirtLining") String skirtLining,
            @Field("PrmSkirtUmbrella") String skirtUmbrella,
            @Field("PrmHangings") String hangings,
            @Field("PrmOpen") String open,
            @Field("PrmPiping") String piping,
            @Field("PrmPipingLining") String pipingLining,
            @Field("PrmTassels") String tassels,
            @Field("PrmIsSpeakerNet") String isSpeakerNet,
            @Field("PrmIsSpeakerNetLining") String isSpeakerNetLining,
            @Field("PrmIsWorkPiece") String isWorkPiece,
            @Field("PrmIsSkirtLining") String isSkirtLining,
            @Field("PrmIsPiping") String isPiping,
            @Field("PrmIsPipingLining") String isPipingLining,
            @Field("PrmIsSkirtSpeakerNet") String isSkirtSpeakerner,
            @Field("PrmIsSkirtSpeakerNetLining") String isSkirtSpeakerNetLining,
            @Field("PrmSkirtOthers") String skirtOthers,
            @Field("PrmIsSkirt") String IsSkirt,
            @Field("PrmZipHidingVal") String ZipHidingVal,
            @Field("PrmZipNomalVal") String ZipNomalVal,
            @Field("PrmSkirtMeasure") String SkirtMeasure,
            @Field("PrmIsStraight") String IsStraight,
            @Field("PrmAlterationType") String alterType
    );

    @FormUrlEncoded
    @POST("WebAppOrdermeasure.asmx/FnGetFabricTypeList")
    Call<JsonElement> getFabricTypeList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId
    );

    @GET("WebAppOrdermeasure.asmx/FnGetFunctionList")
    Call<JsonElement> getFunctionList();

    @GET("WebAppOrdermeasure.asmx/FnGetPriorityList")
    Call<JsonElement> getPriorityList();

    @FormUrlEncoded
    @POST("WebAppOrdermeasure.asmx/FnGetDesignerList")
    Call<JsonElement> getDesignerTypeList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId
    );

    @FormUrlEncoded
    @POST("WebAppOrdermeasure.asmx/FnOrderMeasureList")
    Call<JsonElement> getOrderMeasureList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmId") String Id,
            @Field("PrmOrderNo") String OrderNo,
            @Field("PrmDressType") String DressType
    );

    @FormUrlEncoded
    @POST("WebAppOrder.asmx/FnOrderListAccountWise")
    Call<JsonElement> getExistingOrderList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmAccId") String AccId
    );

    @FormUrlEncoded
    @POST("WebAppCustomerMeasurement.asmx/FnCustomerMeasurementList")
    Call<JsonElement> getCustomerMeasurementList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmAccId") String AccId
    );

    @FormUrlEncoded
    @POST("WebAppCustomerMeasurement.asmx/FnSaveCustomerMeasurement")
    Call<JsonElement> saveCustomerMeasurement(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmCustomerId") String CustId,
            @Field("PrmTightFit") String TightFit,
            @Field("PrmMedium") String Medium,
            @Field("PrmLooseFit") String LooseFit,
            @Field("PrmTopShort") String TopShort,
            @Field("PrmTopLongCh") String TopLongCh,
            @Field("PrmTopLongAnr") String TopLongAnr,
            @Field("PrmTopLongUm") String TopLongUm,
            @Field("PrmTopShoulder") String TopShoulder,
            @Field("PrmTopFb1") String TopFb1,
            @Field("PrmTopFb2") String TopFb2,
            @Field("PrmTopShw") String TopShw,
            @Field("PrmTopYoke") String TopYoke,
            @Field("PrmTopChest1") String TopChest1,
            @Field("PrmTopChest2") String TopChest2,
            @Field("PrmTopChest3") String TopChest3,
            @Field("PrmTopReady1") String TopReady1,
            @Field("PrmTopReady2") String TopReady2,
            @Field("PrmTopReady3") String TopReady3,
            @Field("PrmTopPoint1") String TopPoint1,
            @Field("PrmTopPoint2") String TopPoint2,
            @Field("PrmTopShape") String TopShape,
            @Field("PrmTopSlit1") String TopSlit1,
            @Field("PrmTopSlit2") String TopSlit2,
            @Field("PrmTopSlitR") String TopSlitR,
            @Field("PrmTopSleeves1") String TopSleeves1,
            @Field("PrmTopSleeves2") String TopSleeves2,
            @Field("PrmTopSleevesFull") String TopSleevesFull,
            @Field("PrmTopQtr1") String TopQtr1,
            @Field("PrmTopQtr2") String TopQtr2,
            @Field("PrmTopQtr3") String TopQtr3,
            @Field("PrmTopQtr4") String TopQtr4,
            @Field("PrmTopSleevesSub1") String TopSleevesSub1,
            @Field("PrmTopSleevesSub2") String TopSleevesSub2,
            @Field("PrmTopSleevesSub3") String TopSleevesSub3,
            @Field("PrmTopArmHole1") String TopArmHole1,
            @Field("PrmTopArmHole2") String TopArmHole2,
            @Field("PrmTopArmRound1") String TopArmRound1,
            @Field("PrmTopArmRound2") String TopArmRound2,
            @Field("PrmTopNeckF") String TopNeckF,
            @Field("PrmTopNeckB") String TopNeckB,
            @Field("PrmTopNeckW") String TopNeckW,
            @Field("PrmTopNeckSquare") String TopNeckSquare,
            @Field("PrmTopFlair1") String TopFlair1,
            @Field("PrmTopFlair2") String TopFlair2,
            @Field("PrmBottomLength") String BottomLength,
            @Field("PrmBottomWaistR") String BottomWaistR,
            @Field("PrmBottomHip") String BottomHip,
            @Field("PrmBottomSalwar") String BottomSalwar,
            @Field("PrmBottomChuri1") String BottomChuri1,
            @Field("PrmBottomChuri2") String BottomChuri2,
            @Field("PrmBottomChuri3") String BottomChuri3,
            @Field("PrmBottomChuri4") String BottomChuri4,
            @Field("PrmBottomParallel1") String BottomParallel1,
            @Field("PrmBottomParallel2") String BottomParallel2,
            @Field("PrmBottomBell1") String BottomBell1,
            @Field("PrmBottomBell2") String BottomBell2,
            @Field("PrmBottomBell3") String BottomBell3,
            @Field("PrmRemarks") String Remarks
    );

    @FormUrlEncoded
    @POST("WebAppCustomerMeasurement.asmx/FnUpdateCustomerMeasurement")
    Call<JsonElement> updateCustomerMeasurement(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmFaId") String FaId,
            @Field("PrmUserId") String UserId,
            @Field("PrmId") String Id,
            @Field("PrmCustomerId") String CustId,
            @Field("PrmTightFit") String TightFit,
            @Field("PrmMedium") String Medium,
            @Field("PrmLooseFit") String LooseFit,
            @Field("PrmTopShort") String TopShort,
            @Field("PrmTopLongCh") String TopLongCh,
            @Field("PrmTopLongAnr") String TopLongAnr,
            @Field("PrmTopLongUm") String TopLongUm,
            @Field("PrmTopShoulder") String TopShoulder,
            @Field("PrmTopFb1") String TopFb1,
            @Field("PrmTopFb2") String TopFb2,
            @Field("PrmTopShw") String TopShw,
            @Field("PrmTopYoke") String TopYoke,
            @Field("PrmTopChest1") String TopChest1,
            @Field("PrmTopChest2") String TopChest2,
            @Field("PrmTopChest3") String TopChest3,
            @Field("PrmTopReady1") String TopReady1,
            @Field("PrmTopReady2") String TopReady2,
            @Field("PrmTopReady3") String TopReady3,
            @Field("PrmTopPoint1") String TopPoint1,
            @Field("PrmTopPoint2") String TopPoint2,
            @Field("PrmTopShape") String TopShape,
            @Field("PrmTopSlit1") String TopSlit1,
            @Field("PrmTopSlit2") String TopSlit2,
            @Field("PrmTopSlitR") String TopSlitR,
            @Field("PrmTopSleeves1") String TopSleeves1,
            @Field("PrmTopSleeves2") String TopSleeves2,
            @Field("PrmTopSleevesFull") String TopSleevesFull,
            @Field("PrmTopQtr1") String TopQtr1,
            @Field("PrmTopQtr2") String TopQtr2,
            @Field("PrmTopQtr3") String TopQtr3,
            @Field("PrmTopQtr4") String TopQtr4,
            @Field("PrmTopSleevesSub1") String TopSleevesSub1,
            @Field("PrmTopSleevesSub2") String TopSleevesSub2,
            @Field("PrmTopSleevesSub3") String TopSleevesSub3,
            @Field("PrmTopArmHole1") String TopArmHole1,
            @Field("PrmTopArmHole2") String TopArmHole2,
            @Field("PrmTopArmRound1") String TopArmRound1,
            @Field("PrmTopArmRound2") String TopArmRound2,
            @Field("PrmTopNeckF") String TopNeckF,
            @Field("PrmTopNeckB") String TopNeckB,
            @Field("PrmTopNeckW") String TopNeckW,
            @Field("PrmTopNeckSquare") String TopNeckSquare,
            @Field("PrmTopFlair1") String TopFlair1,
            @Field("PrmTopFlair2") String TopFlair2,
            @Field("PrmBottomLength") String BottomLength,
            @Field("PrmBottomWaistR") String BottomWaistR,
            @Field("PrmBottomHip") String BottomHip,
            @Field("PrmBottomSalwar") String BottomSalwar,
            @Field("PrmBottomChuri1") String BottomChuri1,
            @Field("PrmBottomChuri2") String BottomChuri2,
            @Field("PrmBottomChuri3") String BottomChuri3,
            @Field("PrmBottomChuri4") String BottomChuri4,
            @Field("PrmBottomParallel1") String BottomParallel1,
            @Field("PrmBottomParallel2") String BottomParallel2,
            @Field("PrmBottomBell1") String BottomBell1,
            @Field("PrmBottomBell2") String BottomBell2,
            @Field("PrmBottomBell3") String BottomBell3,
            @Field("PrmRemarks") String Remarks
    );

    @FormUrlEncoded
    @POST("WebAppOrdermeasure.asmx/FnGetDueDate")
    Call<JsonElement> getDueDate(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmWorkType") String workType,
            @Field("PrmOrderNo") String OrderNo,
            @Field("PrmDressType") String DressType
    );

    @FormUrlEncoded
    @POST("WebAppOrder.asmx/FnOrderList")
    Call<JsonElement> OrderList(
            @Field("PrmCmpId") String CmpId,
            @Field("PrmBrId") String BrId,
            @Field("PrmOrderNo") String OrderNo
    );

}
