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
                                발급파일일련번호 :  {{item.발급파일일련번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                신청번호 :  {{item.신청번호 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                파일Id :  {{item.파일Id }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                파일명 :  {{item.파일명 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                파일경로명 :  {{item.파일경로명 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                파일확장자명 :  {{item.파일확장자명 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                파일크기 :  {{item.파일크기 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                파일순서 :  {{item.파일순서 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                민원접수id :  {{item.민원접수id }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                FileType :  {{item.fileType }}
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
        name: 'IssueIssurancePicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/issurances'))
            if(temp.data) {
                me.list = temp.data._embedded.issurances;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/issurances/' + id))
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
                    obj['발급파일일련번호'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

