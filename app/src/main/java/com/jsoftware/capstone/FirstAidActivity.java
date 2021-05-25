package com.jsoftware.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class FirstAidActivity extends AppCompatActivity {

    private TextView txtView1, txtView2, txtView3, txtView4;
    private ImageView imgView1;
    private String caseImg, s;
    private int video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);

        txtView1 = findViewById(R.id.txtView1);
        txtView2 = findViewById(R.id.txtView2);
        txtView3 = findViewById(R.id.txtView3);
        txtView4 = findViewById(R.id.txtView4);
        imgView1 = findViewById(R.id.imgView1);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        Intent prevIntent= getIntent();
        Bundle b = prevIntent.getExtras();
        s = b.get("name").toString();

        populateViews(b.get("name").toString());

        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + video;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

    }

    private void populateViews(String name) {
        switch (name){
            case "Heart Attack":
                txtView1.setText("Heart Attack");
                txtView2.setText("The heart is a muscle which pumps blood around the body. Like every other muscle in the body, it requires a good blood supply to ensure enough oxygen & nutrients are delivered and waste metabolic products (such as Carbon Dioxide) are removed. \nThe heart receives its blood supply from the coronary arteries which branch off from the aorta (the main artery in your body). \nIf a coronary artery becomes blocked (e.g: due to a clot), then the heart muscle beyond the point of the blockage will not receive an adequate blood supply. This will result in death of the heart muscle. \nThe medical term for a heart attack is ‘myocardial infarction’ (myocardium means heart muscle, infarction is tissue death due to lack of oxygen) \n \nHow do I recognise a heart attack? \nCommon signs & symptoms include: \nCentral chest pain, which may spread to the arms/jaw/back/abdomen. Does not ease or go away. \nShortness of breath \nCasualty becomes pale and sweaty \nFear and anxiety \nIrregular or weak pulse \nNot all of these symptoms may be present. In fact, some heart attacks can be ‘silent’ with very little pain which is often mistaken for indigestion. There has been some research which suggests ‘silent’ MIs are more common amongst women and diabetic patients. \nIf you have any reason to suspect a heart attack, you should treat for one. Its always better to be ‘safe than sorry’.");
                txtView3.setText("First aid treatment:");
                txtView4.setText("Step 1: Call an ambulance/emergency medical help, say that you suspect someone is having a heart attack. \n\nStep 2: Make the person comfortable, if possible ask them to sit on the floor. The best position is known as the “W” position, this involves the person sitting up with something under their knees to raise them. This reduces the strain on the heart. \n\nStep 3: If you are able to, ask the casualty to chew on a 300mg (big) aspirin. If they have any other medication for their heart (a spray etc.) which a doctor has told them to use, then let the casualty use it. \n\nThe casualty may lose consciousness before the ambulance arrives. Try to be reassuring and calm, the casualty will be incredibly frightened and anxious which could aggravate their condition. Be prepared that the casualty may suffer a cardiac arrest (the heart stops beating) and you may have to perform Cardiopulmonary Resuscitation.");
                imgView1.setImageResource(R.drawable.case0pic);
                video = R.raw.case0;
                break;
            case "Anaphylaxis":
                txtView1.setText("Anaphylaxis");
                txtView2.setText("Most of us are allergic to something whether it be pets, pollen in the air or certain foods. Normally, our allergic reaction consists of mild discomfort and irritation and doesn’t cause severe problems. \nHowever, an increasing number of people suffer from a serious allergy known as anaphylaxis. This is a life threatening whole body allergic reaction. There can be a whole range of triggers such as peanuts, shellfish, latex etc. \nWhen the body detects this allergen, it reacts by releasing a large amount of inflammatory substances such as histamine. These substances cause blood vessels throughout the body to widen, leading to a drop in blood pressure, and airways in the lungs to constrict and secrete mucus causing severe difficulty in breathing.\nThis often happens very quickly, possibly even seconds after coming into contact with the allergen. However the reaction can in rare cases be delayed up to 24 hours after the initial contact.\n\nHow do I recognise anaphylaxis?\nFirst of all, the person may tell you that they are allergic to something. Otherwise they may wear a bracelet around their wrist/neck/ankle with details of the nature and severity of their allergy. \nThe following are signs & symptoms of anaphylaxis. Remember each reaction will be different, and a person may not display all of these signs.\nDifficulty in breathing & speaking\nGeneralised rash and swelling over the whole body\nSwollen tongue\nFeeling faint/dizzy possibly leading to collapse\nNausea and vomiting\nSigns of shock – pale/cold/clammy/weak pulse\nThe difficulty in breathing is caused by constriction of the person’s airway. In addition, they can suffer a severe drop in blood pressure leading to them feeling faint and possibly collapsing.");
                txtView3.setText("First aid treatment:");
                txtView4.setText("Step 1: Call an ambulance immediately.\n\nStep 2: If the casualty is carrying any medication, such as an auto-injector containing adrenaline, assist them to use it. The most common auto-injector is the Epipen however there are a variety of other brands in existence.\n\nStep 3: If the casualty starts showing signs of shock, then lie them down with their legs raised to increase blood flow to the brain and vital organs. Otherwise, keep the casualty in the most comfortable position to help with their breathing.\n\nStep 4: Reassure the casualty as they may be very anxious and frightened. Be prepared that they may collapse and stop breathing. If they do then you should commence Cardiopulmonary resuscitation (CPR) immediately.\n\n");
                imgView1.setImageResource(R.drawable.case1pic);
                video = R.raw.case1;
                break;
            case "Stroke":
                txtView1.setText("Stroke");
                txtView2.setText("What is a stroke?\nA stroke (or ‘CVA’ – cerebrovascular accident) is a problem with the blood supply to the brain. The brain has a large and complex system of arteries and veins supplying it with blood.\n\nBroadly, there are two types of stroke:\nIschemic stroke: A clot blocks an artery in the brain causing death of brain tissue.\n\nHemorrhagic stroke: An artery in the brain ruptures causing bleeding. For more information, take a look at this excellent animation from NHS choices. ");
                txtView3.setText("Recognising a stroke");
                txtView4.setText("If you suspect a stroke, there is a simple test you can use – The FAST test:\nFace: Does the person have any facial weakness? Can they smile evenly? Does one side of their face appear to droop?\n\nArms: Can the person raise both arms?\n\nSpeech: Can the person speak clearly?\n\nTime: Time to call an ambulance if the person fails any of the above tests. You should not delay in calling an ambulance if you suspect a stroke.\n\n");
                imgView1.setImageResource(R.drawable.case2pic);
                video = R.raw.case2;
                break;
            case "Diabetes":
                txtView1.setText("Diabetes");
                txtView2.setText("Broadly speaking, diabetes is a problem with the body’s control of blood sugar levels. The body produces a variety of hormones to control blood sugar. The most important is insulin which is released by the pancreas. Insulin acts to reduce blood sugar levels.\n\nThere are two main types of diabetes:\n\nType 1: In this case, the body fails to produce enough insulin. Typically onset occurs in childhood however this is not always the case. Type 1 diabetes is often called Insulin Dependent Diabetes Mellitus (IDDM).\n\nType 2: In type 2 diabetes, the pancreas produces enough insulin however cells in the body become resistant to insulin so its effects are reduced.  Type 2 diabetes is more common in later life, and may be affected by various lifestyle factors such as diet and exercise.\n\nIn both cases, the body has trouble regulating its blood sugar levels. An undiagnosed diabetic will often first present with high blood sugar (Hyperglycemia)");
                txtView3.setText("Hyperglycemia and Hypoglycemia");
                txtView4.setText("Hyperglycemia\n\nHigh blood sugar, or hyperglycemia often occurs in undiagnosed diabetics or diabetics who have not taken the correct medication.\n\nThe signs and symptoms of high blood sugar are:\n\nIncreased thirst and urination\nDrowsiness and fatigue\nDry and warm skin\nA ‘fruity’ smell on the breath\nUnconsciousness\n\nHyperglycemia may develop over several days and weeks. If you suspect someone has high blood sugar, you should urge them to seek medical attention. If they become very drowsy or unconscious then phone for an emergency ambulance and place them into the recovery position to protect their airway.\n\nHypoglycemia:\n\nLow blood sugar is known as hypoglycemia. This can occur in diabetics who take too much insulin, or do not eat enough to keep their blood sugar at normal levels. Hypoglycemia can develop very quickly.\n\nThe signs and symptoms of low blood sugar (hypoglycemia) are:\n\nIrritability\nConfusion / irrational behaviour which is out of character\nWeakness and drowsiness\nClammy skin\nReducing level of consciousness\n\nThe person may tell you they have diabetes, or be wearing a medical alert bracelet with details of their medical condition.\n\nIf you suspect someone is suffering from low blood sugar, you should attempt to raise the blood sugar by offering something sweet such as a sugar cube / sugary drink / sugary snack. The casualty may be carrying snacks for such a situation.\n\nIf the casualty has a reduced level of consciousness, or does not respond to the treatment above, you should call an emergency ambulance.\n\nIf someone is unconscious, don’t try to put anything in their mouth (such as a sugar cube) to attempt to raise their blood sugar as this could be a choking risk.");
                imgView1.setImageResource(R.drawable.casepic);
                video = R.raw.case3;
                break;
            case "Seizures":
                txtView1.setText("Seizures");
                txtView2.setText("There are numerous reasons why someone may have a seizure (convulsion / fit). One of the most well known causes is a medical condition called Epilepsy where the electrical activity in the brain is disturbed. However there can be a variety of other causes including:\n\nHead injuries\nDrugs / Alcohol\nPoisoning\nLow blood sugar (Hypoglycaemia)\nIn infants, high temperatures.\n\nSeizures are often characterised by a loss of consciousness followed by uncontrolled muscle contractions. They can be quite frightening for those who haven’t witnessed a seizure before. Sometimes a seizure is preceded by a warning sign known as an “aura”.");
                txtView3.setText("First aid for a seizure");
                txtView4.setText("Step 1: Remove any objects from around the casualty (chairs, tables etc.) to prevent them injuring themselves. Make the area as safe as practically possible\n\nStep  2: Protect the casualty’s head by padding around it and underneath the neck. Don’t place bulky padding underneath the casualty’s head as this could tilt their head forwards and close their airway.\n\nStep 3: Try and establish a cause for the seizure. Are they a known to suffer from Epilepsy? Do they have Diabetes? Have they had an injury recently? Look for medical alert bracelets on the casualty’s wrists/ankles/neck which may contain details of any medical conditions.\n\nStep 4: Call an ambulance unless the casualty is known to have seizures regularly and doesn’t normally go to hospital.\n\nStep 5: Note how long the seizure lasts for, and whether there are any gaps.\n\nStep 6: When the seizure stops, open the casualty’s airway by tilting their head backwards and check for normal breathing for up to 10 seconds. If they are breathing then roll them onto their side to protect their airway. If they are not breathing then commence cardiopulmonary resuscitation.\n\nRemember to protect and maintain the casualty’s dignity throughout. Move on any crowds of people and try to offer some privacy. Although the person having the seizure is unconscious, they may still be able to hear or have an awareness of people around them. When the casualty wakes up they may be very confused. Speak slowly and clearly and explain what has happened.");
                imgView1.setImageResource(R.drawable.case4pic);
                video = R.raw.case4;
                break;
            case "Asthma":
                txtView1.setText("Asthma");
                txtView2.setText("\nWhat is Asthma?\n\nThe body has a system of tubes (known as airways) to carry air from our mouth/nose to our lungs. The largest of these is our trachea (known as windpipe). This then splits into two bronchi which then split into a network of tiny bronchioles. This network of bronchioles delivers air to the tiny sacs where gas exchange takes place (known as alveoli).\n\nAsthma is an inflammatory condition of the smallest airways – the bronchioles. These tiny tubes can become inflamed and secrete excessive amounts of mucous causing severe difficulty in breathing. This is known as an asthma attack. The cause of asthma is thought to be a combination of environmental and genetic factors.\n\nAn asthma attack is normally triggered by something, whether it be an allergen (e.g: pollen, dust, vehicle emissions, soot etc.) or an environmental condition such as cold air. Normally, an asthma attack is characterised by the following symptoms:\n\nA wheezing sounds when breathing\nDifficulty in breathing\nUnable to complete a full sentence\nHyperventilation\nAnxiety and panic\nReducing levels of consciousness\n\nRemember not all of these signs and symptoms may be obvious.\n\nThe casualty may wear a bracelet on their wrist/ankle/neck with details of their asthma. Also, they may carry their medication with them.\n\nIf the casualty becomes unconscious, then open their airway by tilting their head back and check for normal breathing. If they are breathing  then roll them onto their side to protect their airway. If they are not breathing then commence cardiopulmonary resuscitation (CPR).\n\nAsthma attacks can appear frightening but you should make every effort to remain calm and in-control of the situation.");
                txtView3.setText("First aid for an asthma attack");
                txtView4.setText("Step 1: Sit the casualty down in the position they find most comfortable for their breathing.\n\nStep 2: Find their medication, which is normally an inhaler (possibly with a spacer device) containing a drug such as Salbutamol.\n\nStep 3: Assist the casualty to use their medication. They should know how many doses to take and how to use the inhaler.\n\nStep 4: Provide reassurance and help calm the person’s breathing if they are hyperventilating.\n\nIf the medication does not have any effect, the casualty starts to become drowsy/exhausted or if they have forgotten their medication then you should call an emergency ambulance immediately.\n\n");
                imgView1.setImageResource(R.drawable.case5pic);
                video = R.raw.case5;
                break;
            case "Fractures":
                txtView1.setText("Fractures");
                txtView2.setText("What is a fracture?\n\nA fracture is the same as a broken bone and the two terms can be used interchangeably. A fracture can occur for many reasons, often due to trauma or excess force being placed on the bone. Some rare medical conditions can also lead to an increased risk of fractures.\n\nGenerally, you can have several different types of fractures in first aid:\n\n> Open fracture: This is where the bone has pierced the skin and caused an open wound which may bleeding. There is a high risk of infection with these injuries\n\n> Closed fracture: A fracture which has not caused an open wound\n\n> Complicated fracture: A fracture which damages nerves and blood vessels, this can cause permanent nerve damage or loss of blood supply to a limb.\n\n> ‘Greenstick‘ fracture: Common in children as their bone’s are more “bendy”, therefore their bones are less likely to fully break. Instead the bone splinters.\n\nSigns and symptoms of fractures\n\nIf someone has a fracture / broken bone, you may see any of the following:\n\nPain\nLoss of movement of the limb / area\nAngulation\nSwelling & bruising (this may not occur immediately)\nTenderness over the area\n\nFractures may not always be incredibly painful, the pain may take a few hours to develop. Sometimes a fracture can be mistaken for a sprain or a strain.");
                txtView3.setText("First aid for fractures");
                txtView4.setText("If you suspect someone has a fracture, then you should follow the first aid steps below:\n\n1) Keep the area as still as possible, ask the person not to move it\n\n2) Expose the site of injury to check for any bleeding or open wounds. If there is a bone through the skin then do not move it, instead cover with a sterile dressing if available. If the wound is bleeding then apply gentle pressure around the wound.\n\n3) Pad around the injured area with blankets / clothing. If the person has hurt their arm then ask them to hold it in the most comfortable position possible\n\n4) Quickly arrange transport to the nearest hospital.\n\nCall an emergency ambulance if:\n\nYou cannot move the person (for example, because they have hurt their leg)\nThey have fractured a limb and it is turning blue / cold, this indicates a loss of blood supply to the limb which needs correcting urgently\nThe casualty is in severe pain\nYou suspect they may have other serious injuries\nYou are unsure");
                imgView1.setImageResource(R.drawable.case6pic);
                video = R.raw.case6;
                break;
            case "Cuts and grazes":
                txtView1.setText("Cuts and grazes");
                txtView2.setText("We’ve all had them, simple cuts and grazes are a common first aid problem. The medical term for a graze is an ‘abrasion’ where only the superficial layers of the skin are damaged.");
                txtView3.setText("First aid for cuts and grazes");
                txtView4.setText("Step 1: Stop any bleeding. You can elevate the wound or apply direct pressure using a sterile gauze swab/pad.\n\nStep 2: Clean the wound using running water or antiseptic wipes. Dry with a sterile gauze swab/dressing.\n\nStep 3: Ensure the area around the wound is clean (no dirt, grit etc.)\n\nStep 4: Cover with a sterile dressing/plaster. Remember some people might be allergic to regular plasters!\n\nYour main aim is to stop the wound becoming infected, so cleaning the area thoroughly is important. Infected wounds are often very red, swollen, painful and may have pus. As the infection develops you may develop a fever. If you see any of these signs you should seek medical advice urgently.\n\nOne of the infection risks in any open wound is Tetanus. Tetanus is caused by Clostridium tetani, a bacteria which lives in soil which can lead to muscle spasms, fever and sweating. There is a vaccine course available so make sure you’ve had all your jabs. \n\nIf you’re unsure about anything, always seek medical advice. Large cuts and grazes will require medical attention to avoid scarring and infection.");
                imgView1.setImageResource(R.drawable.casepic);
                video = R.raw.case7;
                break;
            case "Dog bites":
                txtView1.setText("Dog bites");
                txtView2.setText("Dogs are not always man’s best friend! Dog bites can produce nasty injuries, especially in young children and babies. As well as the obvious injury caused, dog bites carry an extra risk from infection. One of the most well-known diseases carried by dogs is rabies, carried in infected animals’ saliva. You can learn more about Rabies from the World Health Organization.");
                txtView3.setText("First aid for a dog bite");
                txtView4.setText("You can treat a dog bite by following the simple first aid advice below.\n\nStep 1: Using clean water (with soap/an antiseptic product if possible) wash the wound thoroughly. Make sure to clean the area around the wound as well.\n\nStep 2: Cover the wound with a sterile wound dressing. If the bleeding is severe, apply direct pressure and call for an ambulance.\n\nStep 3: If there is a risk of infection (ie, you are in a rabies risk area) or the wound appear serious, then seek medical advice immediately. The casualty may require an injection to protect them from the rabies virus.\n\nIf you are concerned about rabies, you can find a map of areas at risk online\n\nIf you are concerned that the dog may still be in the vicinity and poses a danger to yourself then call the police immediately. Do not try to capture or calm the dog down, as this may result in further injury to yourself or others.\n\nThe advice above can also apply to any animal / human bites, not just dogs.");
                imgView1.setImageResource(R.drawable.case8pic);
                video = R.raw.case8;
                break;
            case "Fainting":
                txtView1.setText("Fainting");
                txtView2.setText("A faint is a brief and sudden loss of consciousness, normally due to a reduction in the blood flow to the brain. Normally, a faint results in a person falling to the floor.\n\nThe brain requires a constant supply of Oxygen and Nutrients, this supply is provided by the blood. There are numerous things that can interrupt this supply. For example, blood tends to pool in the legs during periods of inactivity (e.g: standing or sitting for long periods of time). If you suddenly stand up, the heart has to work harder to pump this blood upwards against gravity. This can cause a “head rush” in some people, with a feeling of dizziness. However in other people, this interruption in the blood supply to the brain causes them to lose consciousness – a faint.\n\nGenerally once a person has fainted and fallen to the floor, they regain consciousness very quickly. This is because when lying down, the heart finds it easier to pump blood to the brain as it isn’t working against gravity.\n\nThere are some tell-tale signs that someone is going to faint. They may go very pale/white, and look unsteady on their feet. Also they may complain of feeling ‘light headed’ or ‘funny’.");
                txtView3.setText("Treatment for a faint");
                txtView4.setText("If someone complains of feeling faint you should sit them down on the floor if possible until they feel better. This is to prevent any injuries occurring if they do fall.\n\nIf someone has fainted, you should:\n\nRaise their legs to improve the blood supply to the brain.\nIf they’ve fallen, check for any injuries such as fractures or head injuries.\nOnce they recover, help them sit up gradually. Don’t let the casualty stand up straight away as they may just faint again!\n\nIf they casualty does not wake up, you should open their airway by tilting their head backwards and check to see if they’re breathing. If they are, roll them onto their side and call an ambulance.\n\nIf the casualty recovers fully and hasn’t suffered any injuries, there is no need to call an ambulance. If they are not sure what caused the faint, or haven’t fainting before then it is advisable for them to seek medical attention. Occasionally, a faint can be the sign of a more serious underlying medical condition.\n\nIf someone is fainting on a regular without any known cause, they should be encouraged to seek urgent medical advice.");
                imgView1.setImageResource(R.drawable.fa);
                video = R.raw.case9;
                break;
            case "Nosebleed":
                txtView1.setText("Nosebleed");
                txtView2.setText("Every parents favourite! The official medical name for a nosebleed is “epistaxis“.\n\nThe nose has an abundant blood supply to help warm and moisten air when we breathe in. However, this makes it vulnerable.\n\nThere are countless causes for nosebleeds, some common ones include:\n\nTrauma\nHigh blood pressure (hypertension)\nBlood thinning medication (e.g: Warfarin)\nForeign bodies (fingers!)\nInflammation");
                txtView3.setText("Treatment of a nosebleed");
                txtView4.setText("Step 1: Ask the person to tilt their head forwards and pinch the soft part of their nose for ten minutes. Encourage them not to pick at their nose or sniff. Ask them to breathe through their mouth.\n\nStep 2: After 10 minutes release the pressure and see if the bleeding has stopped. If it hasn’t, reapply the pressure. You can also try placing something cool on the nose (ice pack etc.) to constrict the blood vessels.\n\nStep 3: Once the bleeding has stopped, clean up any blood from around the face/mouth. Tell the person not to sneeze/pick at their nose/sniff for several hours as this could cause the bleeding to start again.\n\nIt can be very difficult to get a child to sit still and hold their nose, so try your best. In some cases you might have to hold their nose for them.\n\nIf the bleeding does not stop (after around 20 – 30 minutes), you should seek medical advice. In addition, if the cause of the nosebleed is unclear or they are recurring you should also seek advice.\n\nA nosebleed after a head injury can be the sign of a more serious injury, therefore urgent medical assistance should be sought.\n\nI thought someone with a nosebleed should tilt their head backwards?\n\nA common misconception is that you should tilt head backwards. This isn’t recommended as blood will travel back down into your mouth and into your stomach. This can make you feel sick. ");
                imgView1.setImageResource(R.drawable.casepic);
                video = R.raw.case10;
                break;
            case "Wasp stings":
                txtView1.setText("Wasp stings");
                txtView2.setText("The first aid for a wasp sting is incredibly simple\n\n1) Watch for any signs of  a serious allergic reaction known as ‘anaphylaxis’ – things to watch out for include difficulty in breathing, severe swelling and redness over the whole body (anaphylaxis was discussed in the medical conditions module of this course). If you suspect they are having an allergic reaction then call an ambulance immediately.\n\n2) If the ‘stinger’ is still visible in the skin then try to remove it. It’s best to try to brush the stinger away using a credit card / fingernail rather than using tweezers.\n\n3) Cool the area using an ice pack / bag of frozen peas / cold compress. This will help reduce the swelling and the pain.\n\n3) Cool the area using an ice pack / bag of frozen peas / cold compress. This will help reduce the swelling and the pain.\n\nIf you are concerned at all about the sting then seek medical attention. If you suspect someone may be having a severe allergic reaction then you should call an ambulance immediately. Be wary of stings to the mouth / tongue as these can obstruct a person’s breathing, always seek medical attention.");
                txtView3.setText("");
                txtView4.setText("");
                imgView1.setImageResource(R.drawable.casepic);
                video = R.raw.case11;
                break;
            case "Major Bleeding":
                txtView1.setText("Dealing With Major Bleeding");
                txtView2.setText("Major bleeding is life-threatening and requires urgent first aid intervention to prevent further blood loss and the development of shock. ");
                txtView3.setText("First Aid Steps for Major Bleeding");
                txtView4.setText("Expose injury and elevate above level of the heart\n\nIf there are no foreign objects, apply firm direct pressure over the wound\n\nIf there is an object, apply pressure around the foreign object\n\nCall for emergency medical help\n\nA useful mnemonic to help you remember the first aid steps for major bleeding is ‘PEEP‘.\n\nP: Position – position the victim in a safe / comfortable position\n\nE: Elevate limbs\n\nE: Expose & examine the injury. Check for any embedded or foreign objects such as pieces of glass.\n\nP: Pressure – apply direct pressure over the injury to control blood loss\n\nDo not apply a tourniquet unless specifically trained to do so. Do not attempt to wash out a major wound – your aim is to control the bleeding as quickly as possible.  ");
                imgView1.setImageResource(R.drawable.case12pic);
                video = R.raw.case12;
                break;
            case "Puncture Wounds":
                txtView1.setText("Puncture Wounds");
                txtView2.setText("A puncture wound occurs when an object pierces the skin and enters into a tissue of the body. This creates an open wound which is painful and may be bleeding.Puncture wound Punctures may occur due to any sharp objects such as glass, scissors, knives, pins, nails, wood splinters and sharp stones.\n\nThe object may remain embedded in the wound or may have passed clean through the body part involved.\n\nDo not remove the object unless it is very small (for example a small splinter)\nStop the bleeding by applying pressure around the wound – take care not to dislodge the object\nIf possible, elevate the limb to prevent further blood loss\nSeek urgent medical attention\n\nIt is important to keep the object as still as possible to prevent further injury to the deeper structures below the skin. Whilst it is tempting sometimes to attempt to remove the object, this can actually worsen the situation by causing further bleeding and tissue damage. Removal of embedded objects should only be carried out by a medical professional. ");
                txtView3.setText("");
                txtView4.setText("");
                imgView1.setImageResource(R.drawable.case12pic);
                video = R.raw.case11;
                break;
            case "Shock":
                txtView1.setText("Shock");
                txtView2.setText("Shock is a medical emergency which can be caused by severe blood loss. The casualty does not receive enough oxygen and other essential nutrients due to the loss of blood.\n\nRemember that blood is the major transport mechanism in the body for oxygen and vital nutrients. If you’ve lost half your blood volume (on the floor in a puddle) then that blood isn’t available to transport oxygen and nutrients to your important organs. \n\nMedical shock is not the same as emotional shock\n\nMany people are confused about what shock actually means. The mass media use the term ‘shock’ to refer to people who have been emotionally affected by a traumatic incident. \n\nHowever – this is not the same as medical shock. \n\nMedical shock is a life threatening medical emergency. There are various different causes of shock, major blood loss will cause hypovolemic shock. Hypovolemic means low blood volume. \n\n\nHow to recognise shock\n\nPale, cold and clammy skin\nConfusion\nDrowsiness (reducing level of consciousness)\nFast, weak pulse\nFast, shallow breathing\n\nIf a casualty has lost a lot of blood, you should be actively looking for signs and symptoms of shock.");
                txtView3.setText("First aid treatment for shock");
                txtView4.setText("If you suspect a casualty is suffering from shock then you should:\n\nControl any external blood loss (think PEEP from the last unit)\nLie the casualty down and raise their legs if possible\nCover the casualty with a coat or blanket to keep them warm\nCall for emergency medical help\n\nDo not give the casualty anything to eat or drink! Stay with them until medical help arrives. If the casualty becomes unconscious / unresponsive then you should place them into the recovery position. If they stop breathing normally then commence CPR.\n\nThe key skill for a first aider is being able to recognise shock developing early and call for prompt medical assistance. ");
                imgView1.setImageResource(R.drawable.casepic);
                video = R.raw.case14;
                break;
            case "Minor Wounds":
                txtView1.setText("Minor wounds");
                txtView2.setText("Nearly all of us will have suffered a minor wound at some point in our lives. There are some simple first aid steps you can take if someone has suffered a minor wound:\n\nWash your hands and wear gloves\nClean the wound thoroughly with antiseptic wipes or clean running water\nCover the wound using a clean dressing (plaster, non-adherent pad etc.). \n\nCaution: Do not remove any embedded objects (e.g: glass) – seek medical advice if there are any objects in a wound. \n\nMost minor wounds will heal with time and these simple first aid steps. However, sometimes they can become infected. You should watch out for signs and symptoms of an infected wound:\n\nIncreasing pain\nArea around the wound become red, swollen and warm to touch\nThe wound starts producing discharge / pus\nThe casualty develops a temperature / fever\n\nBelow is an example of an infected minor wound (click on the photo to enlarge). You can clearly see the area around the wound has become red and swollen. The wound is also producing a yellow discharge.");
                txtView3.setText("");
                txtView4.setText("");
                imgView1.setImageResource(R.drawable.case12pic);
                video = R.raw.case15;
                break;
            case "Burns":
                txtView1.setText("Classification of burns");
                txtView2.setText("Burn injuries can be classified by their type and depth. The size of burns is expressed as a percentage of total body area.\n\nTypes of burn:\n\nScald – caused by a hot liquid\nFriction – caused by rough surfaces, e.g: carpet\nRadiation/sunburn\nElectrical – will have an entry burn and an exit burn\nChemical\nDry – touching hot objects, e.g: a cooker.\n\nDepths of burn\n\nSuperficial (1st degree) – this is when only the top layer of skin has been damaged. These are the most minor burns. Often the only sign is a reddening of the skin. Minor sunburn falls into this category of burns.\n\nPartial thickness (2nd degree) – these burns cause blisters in the skin due to the damaged tissue releasing fluid.\n\nFull thickness (3rd degree) – this is the most serious type of burn. In a full thickness burn, every layer of the skin has been affected and the underlying bone, muscle or fat may have also been damaged.\n\n\nAssessment of burn size\n\nCorrectly estimating the size of the burn is important. Burn area is measured as a percentage of total body surface area. \n\nThere are two techniques which can be used:Palm of hand: roughly speaking, the palm of the victim’s hand will be approximately 1% of their body surface area\nRule of nines\n\nBurns in the region of 10% of body area are serious and may produce severe shock. ");
                txtView3.setText("Treatment of burns");
                txtView4.setText("Minor burns are incredibly common household injuries. Our homes are full of items which can cause burns – hair straighteners, kettles, toasters, electric heaters. The list is endless. Generally, burns are caused by touching something hot which damages the skin. However, they can also be caused by radiation (for example, from the sun), chemicals, electricity and friction. As well heat, burns can also be caused by the extreme cold – these are known as freeze or ice burns.\n\n\nFirst aid for a minor burn\n\nStep 1: Immediately run the burn under cold running water for a minimum of 10 minutes. If running cold water is not available then improvise! You can use other non-toxic liquids such as drinks. Your aim is to cool the burn.\n\nStep 2: Expose the affected area and if possible remove anything that could be constricted (watches, jewellery etc.). Do not remove clothing sticking to the burn, instead cool through the clothing.\n\nStep 3: After cooling the burn, cover with a non-fluffy dressing/covering. If you have access to a first aid kit, use a non-fluffy sterile dressing. If not then improvise with whatever is available (plastic bag, kitchen clingfilm, teatowel etc.). Be careful not to constrict the burn!\n\nStep 4: Seek medical advice for anything except the most minor of burns. Call an ambulance if serious or if the burn is near the face/neck.\n\n\nWhat not to do\n\nDo not try to remove clothing sticking to a burn, instead cool through the clothing\nDo not apply toothpaste / butter / creams to a burn. Running water is the most effective cooling method.\nException: “Aftersun” lotion is useful for sunburn\nDo not burst any blisters\nDo not stop cooling before 10 minutes is up!\n\n\nChemical burns\n\nThere are a wide variety of chemicals and substances which can cause chemical burns. These can range from household chemicals such as bleach, cleaning products, pesticides to strong industrial chemicals used in the workplace. Chemical burns can be incredibly serious as the chemical will keep damaging the skin and tissue until it is removed.\n\nStep 1: Check for any dangers to yourself. The chemicals that caused the casualty’s injuries could also pose a danger to you. Try to establish what caused the burn and how safe the chemicals are. If you are in an enclosed area, move the casualty outside or open windows to prevent the build up of fumes. Wear disposable gloves.\n\nStep 2: Cool the burn with copious running water for at least 15 – 20 minutes. Ensure that you do not come into contact with the water used to cool the burn as it may contain the harmful chemical.\n\nStep 3: If any clothing has come into contact with the chemical, it should be removed providing it isn’t sticking to the burn. Try to identify the chemical involved and its container.\n\nStep 4: Cover the burn with a sterile, non-fluffy first aid dressing. If no dressing is available, then improvise. Good items to use include cling-film and plastic bags.\n\nIf the burn is serious, the casualty shows signs of shock or the casualty becomes drowsy/looses consciousness then call an emergency ambulance immediately. If a large chemical spill has occurred then also ensure the fire brigade and police have also been informed.\n\nIf you become exposed to the chemical involved then you should seek medical advice immediately.");
                imgView1.setImageResource(R.drawable.case16pic);
                video = R.raw.case16;
                break;
            default:
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
