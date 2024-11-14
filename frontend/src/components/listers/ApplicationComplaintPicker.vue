<template>
    <div>
        <v-list two-line v-if="list.length > 0">
            <v-list-item-group 
                    v-model="selected" 
                    color="primary"
                    @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-avatar color="primary-darker-1">
                        </v-list-item-avatar>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                신청번호 :  {{item.신청번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                서식일렬번호 :  {{item.서식일렬번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                서비스id :  {{item.서비스id }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                서비스분류코드 :  {{item.서비스분류코드 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청인명 :  {{item.신청인명 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                주민등록번호 :  {{item.주민등록번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청처리결과코드 :  {{item.신청처리결과코드 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청일시 :  {{item.신청일시 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                등록일시 :  {{item.등록일시 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                수정일시 :  {{item.수정일시 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                구비서류 :  {{item.구비서류 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청수령방법 :  {{item.신청수령방법 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청서파일 :  {{item.신청서파일 }}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="primary-darker-1"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'ApplicationComplaintPicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/complaints'))
            if(temp.data) {
                me.list = temp.data._embedded.complaints;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/complaints/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    me.list.forEach(function(item, idx) {
                        if(item.name == val.name) {
                            me.selected = idx
                        }
                    })
                }
            }
        },
        methods: {
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    obj['신청번호'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

