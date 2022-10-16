package com.example.petmed;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petmed.Adapter.UserAdapter;
import com.example.petmed.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chat_user_here extends Fragment {

   private RecyclerView recyclerView;
   private UserAdapter userAdapter;
   private List<User> mUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_user_here, container, false);
        recyclerView = view.findViewById(R.id.ChatRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUser = new ArrayList<>();
        readUsers();


//        Intent intent = getActivity().getIntent();
//
//        String otherEditTextEmail = intent.getStringExtra("EditTextEmail Address");
//        String EditTextEmail = auth.getCurrentUser().getEmail();


//        imageButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (editText1.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(),"Write Message Thanks! Fool!.",Toast.LENGTH_SHORT).show();
//                }else{
//                    Map<String, Object> messageData = new HashMap<>();
//                    messageData.put("sender", EditTextEmail);
//                    messageData.put("receiver", otherEditTextEmail);
//                    messageData.put("message", editText1.getText().toString());
//
//                    databaseReference.child("Messages").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            int count;
//                            if(snapshot.exists()){
//                                count = (int) (snapshot.getChildrenCount()+1);
//                            }else{
//                                count = 1;
//
//                            }
//                            databaseReference.child("Chats").child(String.valueOf(count)).setValue(messageData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        editText1.setText("");
//                                    }
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(getActivity(),"Error Sending Messages. Fool!." + e.getMessage() ,Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
//
//            }
//        });
//
//        databaseReference.child("chats").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        if (dataSnapshot.child("sender").getValue().toString().equals(EditTextEmail) || dataSnapshot.child("receiver").getValue().toString().equals(EditTextEmail)) {
//                            String message = dataSnapshot.child("message").getValue().toString();
//                            if (!dataSnapshot.child("sender").getValue().toString().equals(EditTextEmail)){
//                                message = "> " + message;
//                            }
//
//                        }
//                    }
//                    arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,users);
//                    listView1.setAdapter(arrayAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        ImageButton.setOn(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (message.getText().toString().isEmpty()) {
//                    Toast.makeText(ChatActivity.this, "Write a message!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Map<String, Object> messageData = new HashMap<>();
//                    messageData.put("sender", EditTextEmail);
//                    messageData.put("receiver", otherEditTextEmail);
//                    messageData.put("message", message.getText().toString());
//
//                    databaseReference.child("chats").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            int count;
//                            if (snapshot.exists()){
//                                count = (int) (snapshot.getChildrenCount() + 1);
//                            } else {
//                                count = 1;
//                            }
//                            databaseReference.child("chats").child(String.valueOf(count)).setValue(messageData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        message.setText("");
//                                    }
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(ChatActivity.this, "Error in sending message: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
//            }
//        });

//        databaseReference.child("chats").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        if (dataSnapshot.child("sender").getValue().toString().equals(EditTextEmail) || dataSnapshot.child("receiver").getValue().toString().equals(EditTextEmail)) {
//                            String message = dataSnapshot.child("message").getValue().toString();
//                            if (!dataSnapshot.child("sender").getValue().toString().equals(EditTextEmail)){
//                                message = "> " + message;
//                            }
//                            messages.add(message);
//                        }
//                    }
//                    arrayAdapter = new ArrayAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, messages);
//                    chatListView.setAdapter(arrayAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return view;
    }

    private void readUsers() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUser.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);

                     assert user != null;
                     assert firebaseUser != null;
                    if(!user.getId().equals(firebaseUser.getUid())){
                        mUser.add(user);
                    }
                }

                userAdapter = new UserAdapter(getContext(), mUser);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}