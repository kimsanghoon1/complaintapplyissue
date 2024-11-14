<template>

    <v-card outlined>
        <v-card-title>
            ReceiveMethod
        </v-card-title>

        <v-card-text>
            <String label="수령방법코드" v-model="value.수령방법코드" :editMode="editMode"/>
            <String label="수령방법명" v-model="value.수령방법명" :editMode="editMode"/>
            <String label="수령방법구분명" v-model="value.수령방법구분명" :editMode="editMode"/>
            <String label="등록자Id" v-model="value.등록자Id" :editMode="editMode"/>
            <Date label="등록일시" v-model="value.등록일시" :editMode="editMode"/>
            <String label="수령자Id" v-model="value.수령자Id" :editMode="editMode"/>
            <Date label="수령일시" v-model="value.수령일시" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>

<script>

    export default {
        name: 'ReceiveMethod',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    '수령방법코드': '',
                    '수령방법명': '',
                    '수령방법구분명': '',
                    '등록자Id': '',
                    '등록일시': '',
                    '수령자Id': '',
                    '수령일시': '',
                }
            }
            if(typeof this.value === 'object') {
                if(!('수령방법코드' in this.value)) {
                    this.value.수령방법코드 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('수령방법명' in this.value)) {
                    this.value.수령방법명 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('수령방법구분명' in this.value)) {
                    this.value.수령방법구분명 = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('등록자Id' in this.value)) {
                    this.value.등록자Id = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('등록일시' in this.value)) {
                    this.value.등록일시 = '2024-11-14';
                }
            }
            if(typeof this.value === 'object') {
                if(!('수령자Id' in this.value)) {
                    this.value.수령자Id = '';
                }
            }
            if(typeof this.value === 'object') {
                if(!('수령일시' in this.value)) {
                    this.value.수령일시 = '2024-11-14';
                }
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },

        methods: {
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
        }
    }
</script>

