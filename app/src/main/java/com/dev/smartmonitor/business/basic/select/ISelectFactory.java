package com.dev.smartmonitor.business.basic.select;

import com.dev.smartmonitor.persistence.dao.model.Aplicativo;
import com.dev.smartmonitor.persistence.dao.model.ChecagemSistema;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.ConfiguracaoTempoSistema;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.DadosUsoSistema;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoAplicativo;
import com.dev.smartmonitor.persistence.dao.model.NotificacaoSistema;
import com.dev.smartmonitor.persistence.dao.model.Sistema;

import java.util.Date;
import java.util.List;

public interface ISelectFactory {

    public List<Sistema> buscarSistemaAll();

    public Sistema buscarSistemaById(long id);

    public List<ChecagemSistema> buscarChecagemSistemaAll();

    public ChecagemSistema buscarChecagemSistemaById(long id, long idSistema);

    public ChecagemSistema buscarChecagemSistemaByData(Date data);

    public List<ChecagemSistema> buscarChecagemSistemaByData(Date dataInicial, Date dataFinal);

    public List<Aplicativo> buscarAplicativoAll(String ativo);

    public Aplicativo buscarAplicativoById(long id, long idSistema);

    public Aplicativo buscarAplicativoByIdAplicativo(long id);

    public Aplicativo buscarAplicativoByNome(String nome);

    public List<ConfiguracaoTempoAplicativo> buscarConfiguracaoTempoAplicativoAll();

    public ConfiguracaoTempoAplicativo buscarConfiguracaoTempoAplicativoById(long id, long idAplicativo);

    public ConfiguracaoTempoAplicativo buscarConfiguracaoTempoAplicativoByIdAplicativo(long idAplicativo);

    public List<ConfiguracaoTempoSistema> buscarConfiguracaoTempoSistemaAll();

    public ConfiguracaoTempoSistema buscarConfiguracaoTempoSistemaById(long id, long idSistema);

    public ConfiguracaoTempoSistema buscarConfiguracaoTempoSistemaByIdSistema(long idSistema);

    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoAll();

    public DadosUsoAplicativo buscarDadosUsoAplicativoById(long id, long idAplicativo);

    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoByIdAplicativo(long idAplicativo);

    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoByData(Date dataInicial, Date dataFinal);

    public List<DadosUsoAplicativo> buscarDadosUsoAplicativoByDataIdAplicativo(Date dataInicial, Date dataFinal, long idAplicativo);

    public List<DadosUsoSistema> buscarDadosUsoSistemaAll();

    public List<DadosUsoSistema> buscarDadosUsoSistemaByData(Date dataInicial, Date dataFinal);

    public DadosUsoSistema buscarDadosUsoSistemaById(long id, long idSistema);

    public NotificacaoAplicativo buscarNotificacaoAplicativoById(long id, long idAplicativo, long idConfiguracao);

    public List<NotificacaoAplicativo> buscarNotificacaoAplicativoAll(String[] status);

    public List<NotificacaoAplicativo> buscarNotificacaoAplicativoByIdAplicativo(String[] status, long idAplicativo);

    public List<NotificacaoAplicativo> buscarNotificacaoAplicativoByData(String[] status, Date dataInicial, Date dataFinal);

    public NotificacaoAplicativo buscarNotificacaoAplicativoByIdAplicativoData(long idAplicativo, Date data);

    public NotificacaoSistema buscarNotificacaoSistemaById(long id, long idSistema, long idConfiguracao);

    public List<NotificacaoSistema> buscarNotificacaoSistemaAll(String[] status);

    public List<NotificacaoSistema> buscarNotificacaoSistemaByData(String[] status, Date dataInicial, Date dataFinal);

    public NotificacaoSistema buscarNotificacaoSistemaByIdSistemaData(long idSistema, Date data);

}
