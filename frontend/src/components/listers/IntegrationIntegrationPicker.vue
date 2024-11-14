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
                                연계Id :  {{item.연계Id }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청번호 :  {{item.신청번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                서비스Id :  {{item.서비스Id }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                유형일렬번호 :  {{item.유형일렬번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                연계인터페이스 :  {{item.연계인터페이스 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                송신데이터 :  {{item.송신데이터 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                수신데이터 :  {{item.수신데이터 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                결과코드 :  {{item.결과코드 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                결과메시지 :  {{item.결과메시지 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                송신일시 :  {{item.송신일시 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                수신일시 :  {{item.수신일시 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                등록일시 :  {{item.등록일시 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                수정일시 :  {{item.수정일시 }}
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
        name: 'IntegrationIntegrationPicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/integrations'))
            if(temp.data) {
                me.list = temp.data._embedded.integrations;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/integrations/' + id))
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
                    obj['연계Id'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

